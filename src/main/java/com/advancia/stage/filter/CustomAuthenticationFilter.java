package com.advancia.stage.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
    

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		HttpServletRequest httpRequest = request;
        HttpServletResponse httpResponse = response;

        chain.doFilter(request, response);
    }
}
