package edu.hanu.studentManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import edu.hanu.studentManagement.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailService studentDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/teacher").hasRole("TEACHER")
//			.antMatchers("/user").hasAnyRole("TEACHER", "USER")
//			.antMatchers("/", "/login").permitAll().and().formLogin().loginPage("/login");
		
//		http.authorizeRequests().antMatchers(
//                "/js/**",
//                "/css/**",
//                "/images/**",
//                "/fonts/**",
//                "/vendor/**", "/register").permitAll()
//			.and()
//            .formLogin()
//            .loginPage("/login")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .defaultSuccessUrl("/welcome")
//            .failureUrl("/login?error")
//            .permitAll()
//            .and()
//            .logout()
//            .invalidateHttpSession(true)
//            .clearAuthentication(true)
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .logoutSuccessUrl("/login?logout")
//            .permitAll();
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(
	                "/js/**",
	                "/css/**",
	                "/images/**",
	                "/fonts/**",
	                "/vendor/**",
	                "/register",
	                "/login").permitAll()
			// here goes the path that you want to secure
			.antMatchers("/api/**").hasAuthority("USER")
			/////
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/welcome")
				.failureUrl("/login?error=true")
			.and()
			.logout()
	            .logoutUrl("/logout")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
	            .clearAuthentication(true)
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .logoutSuccessUrl("/login");
		
	}

//	@Bean
//	public static PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(10);
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(studentDetailService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
}
