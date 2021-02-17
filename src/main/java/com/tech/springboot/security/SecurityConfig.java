package com.tech.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] SWAGGER_URLS = {
			// -- swagger ui
			"/swagger-ui.html",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/header.jsp/**",
			"/help.jsp/**",
			"/query.jsp/**",
			"/tables.do/**"
			};
	
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
  @Override
  public void configure(HttpSecurity http) throws Exception {
	  
      String path = "/h2-console";
      String antPattern = path.endsWith("/")?path + "**":path + "/**";
      http.authorizeRequests().antMatchers(antPattern).permitAll();
      
	  //Not authorizing request from spring security but changing to JWT

      http.authorizeRequests().antMatchers("/api/**").permitAll();
      http.authorizeRequests().antMatchers(SWAGGER_URLS).permitAll();
	  
	  //All other Authorized
      http.requestMatchers().antMatchers("/private/**").
      and().authorizeRequests()
      .anyRequest()
      .authenticated();
	  
		// Stateless session for APIs
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// set up the csfr token
		http.csrf().disable();
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

  }
}