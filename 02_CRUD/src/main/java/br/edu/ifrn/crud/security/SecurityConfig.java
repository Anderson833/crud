package br.edu.ifrn.crud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrn.crud.Service.UsuarioService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService service;
	
	
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http.authorizeRequests().anyRequest().permitAll(); */
		http.authorizeRequests()
		.antMatchers("/css/**","/imagem/**","/js/**").permitAll()
		.antMatchers("/publico/**").permitAll()
		
		.anyRequest().authenticated()
		.and()
		   .formLogin()
		   .loginPage("/login")
		   .defaultSuccessUrl("/",true)
		   .failureUrl("/login-error")
		   .permitAll()
		.and()
		    .logout()
		    .logoutSuccessUrl("/")
		 .and()
		     .rememberMe();
		 
        
	
}
	
	
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}	
	
	
