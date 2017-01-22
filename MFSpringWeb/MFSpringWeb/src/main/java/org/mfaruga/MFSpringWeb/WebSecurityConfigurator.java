package org.mfaruga.MFSpringWeb;

import org.mfaruga.MFSpringWeb.security.MFAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages = "org.mfaruga.MFSpringWeb")
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("operator").password("password").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("accountant").password("password").roles("ACCOUNTANT");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Setrting authorization rules!!");
		http.authorizeRequests().antMatchers("*.jsp").denyAll().antMatchers("/", "/login").permitAll()
				.antMatchers("/user*//**").access("hasRole('USER') or hasRole('ADMIN')").antMatchers("/admin*//**")
				.access("hasRole('ADMIN')").antMatchers("/second*//**").hasAuthority(MFAuthorities.USER.getAuthority()).antMatchers("/third*//**").access("hasRole('ROLE_USER')").antMatchers("/accountant*//**")
				.access("hasRole('ADMIN') or hasRole('ACCOUNTANT')").and().formLogin()
				.successHandler(authenticationSuccessHandler).failureUrl("/nonAuthorized");
		// .access("hasRole('ADMIN') or
		// hasRole('ACCOUNTANT')").and().formLogin().loginPage("/login")
		// .successHandler(authenticationSuccessHandler).failureUrl("/nonAuthorized");
		// .usernameParameter("username")
		// .passwordParameter("password").loginProcessingUrl("/login").permitAll().and().logout()
		// .logoutSuccessUrl("/").permitAll();

	}

}
