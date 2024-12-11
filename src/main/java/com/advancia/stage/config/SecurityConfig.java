package com.advancia.stage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.advancia.stage.filter.CustomAuthenticationFilter;
import com.advancia.stage.filter.CustomHeaderFilter;
import com.advancia.stage.filter.LoggingFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	 AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
    	 
        http
            .addFilterBefore(new CustomHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new LoggingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(new CustomAuthenticationFilter(authenticationManager), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(authorize -> 
                authorize
                    .anyRequest().authenticated()
            )
            .httpBasic(withDefaults -> {});

        return http.build();
    }
    
    /*
		VERSIONE PRECEDENTE 
		
		    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CustomHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        
        return http.build();
    }
		
     */
}
