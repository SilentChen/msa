package com.silent.msa.admin.util;

import com.silent.msa.admin.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Security Relative's Tool Set.
 * @author: Silent
 * @date: Nov20, 2019
 */
public class SecurityUtils {
    /**
     * login verify
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // execute login verify process
        Authentication authentication = authenticationManager.authenticate(token);

        // verify success then store into context with the verify info
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate token
        token.setToken(JwtTokenUtils.generateToken(authentication));

        return token;
    }

    /**
     * setting authentication into security context holder with http request's token
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * get username with security context's authentication
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        return getUsername(authentication);
    }

    /**
     * get username with given authentication
     * @param authentication
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(null != authentication) {
            Object principal = authentication.getPrincipal();
            if(null != principal && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }

        return username;
    }

    /**
     * get authentication from security context.
     * @return
     */
    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication;
    }
}
