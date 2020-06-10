package com.github.msa.admin.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Authority Package
 * @author Silent
 * @date Nov 20, 2019
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
