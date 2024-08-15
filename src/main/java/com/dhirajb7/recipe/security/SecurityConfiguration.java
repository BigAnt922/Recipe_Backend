package com.dhirajb7.recipe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	//password encoder
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Authentication related - saved in DB
    @Bean
    UserDetailsManager userDetailsManager() {
    	return new UserInfoService();
	}
	
    //UserInfoService can talk to userdetails table if AuthenticationProvider has information about passwordEncoder & UserInfoService
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsManager()); //method name of bean which handles user details manager
    	authenticationProvider.setPasswordEncoder(passwordEncoder());       // method name of bean which handles password encoder
    	return authenticationProvider;
    }
    
  //Authorization  related
  	@Bean
  	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

  		//WORK NEEDS TO BE DONE HERE
  		
  		http.authorizeHttpRequests(config -> config
				.requestMatchers(HttpMethod.GET, "/test/").permitAll()
				.requestMatchers(HttpMethod.POST, "/user/").permitAll()
				.requestMatchers(HttpMethod.GET, "/ingredient/").permitAll()
				.requestMatchers(HttpMethod.GET, "/ingredient/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/recipe/").permitAll()
				.requestMatchers(HttpMethod.GET, "/recipe/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/catagory/").permitAll()
				.requestMatchers(HttpMethod.GET, "/catagory/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/encdec/enc/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/encdec/dec/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/swagger-ui/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/user/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/ingredient/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/ingredient/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/ingredient/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/recipe/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/recipe/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/recipe/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/catagory/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/catagory/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/catagory/**").hasRole("ADMIN"));

  		http.httpBasic(Customizer.withDefaults());

  		http.csrf(a -> a.disable());

  		return http.build();

  	}
}
