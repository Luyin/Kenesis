package com.kenesis.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationDetailsProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationDetailsProvider.class);
   
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	JwtUser userDetails = (JwtUser) authentication.getPrincipal();
    	
        if (null != userDetails) {
            return userDetails;
        } else {
            throw new BadCredentialsException("API token is not valid");
        }
    }
}