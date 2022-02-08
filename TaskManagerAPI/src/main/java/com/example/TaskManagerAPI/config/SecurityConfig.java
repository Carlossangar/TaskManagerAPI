package com.example.TaskManagerAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/tareas").hasAuthority("ADMIN") 
		.antMatchers(HttpMethod.GET, "/tareas/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/tareasUsuario/*").hasAnyAuthority("USER","ADMIN")
		.antMatchers(HttpMethod.PUT, "/estadoTarea").hasAnyAuthority("USER","ADMIN")
		.antMatchers(HttpMethod.GET, "/usuarios").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST, "/tarea").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/tarea/*").hasAuthority("ADMIN")
		.and()
		.httpBasic();

		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		String pwdEncode = new BCryptPasswordEncoder().encode("12345");
		authentication.inMemoryAuthentication()
		.withUser("admin").password(pwdEncode).authorities("ADMIN")
		.and()
		.withUser("user").password(pwdEncode).authorities("USER");
	}
}
