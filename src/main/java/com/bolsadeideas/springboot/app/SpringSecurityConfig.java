package com.bolsadeideas.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bolsadeideas.springboot.app.auth.LoginHandler;
import com.bolsadeideas.springboot.app.auth.filter.JWTAuthenticationFilter;
import com.bolsadeideas.springboot.app.auth.filter.JWTAuthorizationFilter;
import com.bolsadeideas.springboot.app.auth.service.JWTService;
import com.bolsadeideas.springboot.app.models.service.impl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled= true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private LoginHandler successHandler;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/locale").permitAll()
				.anyRequest().authenticated()
				/*
				 * .and() .formLogin() .successHandler(successHandler) .loginPage("/login")
				 * .permitAll() .and() .logout() .permitAll() .and()
				 * .exceptionHandling().accessDeniedPage("/error_403")
				 */
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtService)).csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {

//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//
//		managerBuilder
//				.inMemoryAuthentication()
//				.withUser(userBuilder.username("admin").password("12345").roles("ADMIN", "USER"))
//				.withUser(userBuilder.username("alvaro").password("12345").roles("USER"));

//		managerBuilder
//		.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(new BCryptPasswordEncoder())
//		.usersByUsernameQuery("select username, password, enabled from users where username=?")
//		.authoritiesByUsernameQuery("select u.username, a.authority,u.enabled from authorities a inner join users u on (a.user_id = u.id) where u.username=? ");

		managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
