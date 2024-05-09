package com.dhirajb7.recipe.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, "/test/").permitAll()
				.requestMatchers(HttpMethod.POST, "/user/").permitAll().requestMatchers(HttpMethod.GET, "/ingredient/")
				.permitAll().requestMatchers(HttpMethod.GET, "/ingredient/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/recipe/").permitAll().requestMatchers(HttpMethod.GET, "/recipe/**")
				.permitAll().requestMatchers(HttpMethod.GET, "/catagory/").permitAll()
				.requestMatchers(HttpMethod.GET, "/catagory/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/swagger-ui/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/user/").hasRole("ADMIN").requestMatchers(HttpMethod.GET, "/user/**")
				.hasRole("ADMIN").requestMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/user/").hasRole("ADMIN")
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

	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
