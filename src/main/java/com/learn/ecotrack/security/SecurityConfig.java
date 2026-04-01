package com.learn.ecotrack.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.learn.ecotrack.security.jwt.AuthEntryPointJwt;
import com.learn.ecotrack.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		httpSecurity
		.csrf(csrf->csrf.disable())
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests(request->
		   request
		   .requestMatchers(HttpMethod.POST,"/users/register","/auth/login")
		   .permitAll()
		   .requestMatchers(HttpMethod.GET,"/workshops/**")
		   .permitAll()
		   .requestMatchers(HttpMethod.POST, "/workshops")
		   .hasRole("ADMIN")
		   .requestMatchers(HttpMethod.GET, "/requests","/users")
		   .hasRole("ADMIN")
		   .requestMatchers(HttpMethod.PUT, "/workshops/**",
				   "/requests/*/approve",
				   "/requests/*/reject")
		   .hasRole("ADMIN")
		   .requestMatchers(HttpMethod.DELETE, "/workshops/**")
		   .hasRole("ADMIN")
		   .anyRequest()
		   .authenticated()
		);
		
		httpSecurity.exceptionHandling(authentication->
		         authentication.authenticationEntryPoint(authEntryPointJwt));
		
		httpSecurity.addFilterBefore(authTokenFilter, 
				  UsernamePasswordAuthenticationFilter.class);
		
	    return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
//	globalconfig for cors
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(List.of("http://localhost:5173"));
	        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        config.setAllowedHeaders(List.of("*"));
	        config.setAllowCredentials(true);
    
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	        
	    }
	 
	 @Bean
	 public AuthenticationManager authenticationManager
	 (AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
	

}
