package com.bgc.backgroundVerification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bgc.backgroundVerification.service.UserService;


@Configuration
@EnableWebSecurity 
@Order(2) 
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	UserService userService;
	
	       @Bean
		public DaoAuthenticationProvider daoauthenticationProvider() {

			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(userService);
			auth.setPasswordEncoder(passwordEncoder());
			return auth;
		}
	  
	  
	 @Bean 
	 public BCryptPasswordEncoder passwordEncoder() { 
		 return new BCryptPasswordEncoder();
	  
	  }
	 
	  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(daoauthenticationProvider()); 
		  
	  }
	  
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception { 
		  http.csrf().disable();


		  http.antMatcher("/**").authorizeRequests()
			.antMatchers( "/home","/home/**", "/signup/**","/forgetPassword/**", "/js/**", "/css/**", "/icons/**", "/images/**", "/plugins/**","/assets/**","/scss/**").permitAll()
			.anyRequest().authenticated().and().formLogin().loginPage("/home").loginProcessingUrl("/login").defaultSuccessUrl("/",true).failureUrl("/login?error").permitAll().and().logout()
			.invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll().and()
			.sessionManagement().invalidSessionUrl("/login");;
			
			
		
	  }


		
	  
		  @Override 
		  public void configure(WebSecurity web) throws Exception {
		  web.ignoring().antMatchers("/resources/static/**"); }
		 
	  

	  
  }
	 
	
	
	


