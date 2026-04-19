package com.tutormatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user =User.withUsername("admin")
				.password("{noop}admin")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
		
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login") // Thymeleaf template path
						.loginProcessingUrl("/user/login") // Must match form action
						.defaultSuccessUrl("/tutor-dashboard", true)
						.failureUrl("/login?error")
						.permitAll()
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout")
				);

		return http.build();
	}

}
