package com.bgc.backgroundVerification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bgc.backgroundVerification.service.CandidateLoginService;

@Configuration
@EnableWebSecurity
@Order(1)
public class SpringSecurityUserConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CandidateLoginService userService;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(newpasswordEncoder());
		return auth;
	}

	@Bean
	public PasswordEncoder newpasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* http.csrf().disable(); */

		/*
		 * http.antMatcher().authorizeRequests() .antMatchers("/forgetPassword/**",
		 * "/js/**", "/css/**", "/icons/**", "/images/**", "/plugins/**", "/assets/**")
		 * .permitAll().anyRequest().authenticated().and().formLogin().loginPage(
		 * "/user/login") .loginProcessingUrl("/user/login").defaultSuccessUrl("/",
		 * true).failureUrl("/login?error").permitAll().and()
		 * .logout().invalidateHttpSession(true).clearAuthentication(true)
		 * .logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll().and(
		 * ) .sessionManagement().invalidSessionUrl("/login");
		 */

		http.antMatcher("/user/**").authorizeRequests()
				.antMatchers("/user/forgotPassword**","/user/forgotPassword/**","/user/forgotUsername","/js/**", "/css/**", "/icons/**", "/images/**", "/plugins/**", "/assets/**", "/scss/**").permitAll().anyRequest().hasRole("Candidate").and().formLogin().loginPage("/user/login")
				.defaultSuccessUrl("/user/dashbord", true).permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/user/login").permitAll().and()
				.sessionManagement().invalidSessionUrl("/user/login");

		http.csrf().disable();
	}

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/resources/static/**"); }
	 */

}
