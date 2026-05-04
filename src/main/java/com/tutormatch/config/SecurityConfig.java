package com.tutormatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
		.csrf(csrf -> csrf.disable()) // ✅ FIX NA KULANG
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(   "/",
							    "/static/**",
							    "/css/**",
							    "/js/**",
							    "/images/**",
							    "/login", 
							    "/student-registration",
							    "/tutor-registration",
							    "/users/student-registration",
							    "/users/tutor-registration",
							    "/lesson-registration",
							    "/tutor-match/lessons/**",
							    "/lesson-detail",
							    "/lesson-list",
							    "/lesson-detail.html",
							    "/lesson-list.html").permitAll()
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
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
