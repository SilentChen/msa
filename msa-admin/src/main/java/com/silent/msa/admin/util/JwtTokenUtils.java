package com.silent.msa.admin.util;

import com.silent.msa.admin.security.GrantedAuthorityImpl;
import com.silent.msa.admin.security.JwtAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jwt Tool Object
 * @author Silent
 * @date Nov 20, 2019
 */
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String USERNAME = Claims.SUBJECT;

    private static final String CREATED = "created";

    private static final String AUTHORITIES = "authorities"; // auth list.

    private static final String SECRET = "abcdefgh";

    private static final long EXPIRE_TIME = 12 * 60 * 60 *1000;

    /**
     * Generate Token With Authentication
     * @param authentication
     * @return
     */
    public static String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, SecurityUtils.getUsername(authentication));
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        return generateToken(claims);
    }

    /**
     * Genrate Token With Map Data
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * get claims from given token.
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    /**
     * get username from given token.
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }

        return username;
    }

    /**
     * get token with given http servlet request.
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "bearer ";
        if(null == token) {
            token = request.getHeader("token");
        }else if(token.contains(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        if("".equals(token)) {
            token = null;
        }

        return token;
    }

    /**
     * check the given token's expiration.
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * check the given token's expired and username equal to token's or not.
     * @param token
     * @param username
     * @return
     */
    public static Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * get authentication from given token.
     * @param request
     * @return
     */
    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        String token = JwtTokenUtils.getToken(request);
        if(null != token) {
            if(null != SecurityUtils.getAuthentication()) {
                Claims claims = getClaimsFromToken(token);
                if(null == claims) {
                    return null;
                }
                String username = claims.getSubject();
                if(null == username) {
                    return null;
                }
                if(isTokenExpired(token)) {
                    return null;
                }
                Object authors = claims.get(AUTHORITIES);
                List<GrantedAuthority> autuhorities = new ArrayList<GrantedAuthority>();
                if(null != authors && authors instanceof List) {
                    for(Object object : (List) authors) {
                        autuhorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
                    }
                }
                authentication = new JwtAuthenticationToken(username, null, autuhorities, token);
            }else{
                if(validateToken(token, SecurityUtils.getUsername())) {
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return authentication;
    }
}
