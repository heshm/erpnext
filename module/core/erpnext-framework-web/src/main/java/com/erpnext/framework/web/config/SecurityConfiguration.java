package com.erpnext.framework.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.erpnext.framework.web.service.UserDetailsServiceImpl;

/**
 * Created by Lenovo on 2017/10/12. 访问安全配置类
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
				 //http.headers().frameOptions().disable();
                 http
           /* .antMatcher("/api/**")
            	.authorizeRequests()
            	.antMatchers("/**").authenticated()
                .and()*/
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/authDemo").permitAll()
                .antMatchers("/**").authenticated()
                .and()
            /*.exceptionHandling()
                .accessDeniedPage("/error?403.html")
                .and()*/
            .csrf()
                .ignoringAntMatchers("/druid/**")
                .ignoringAntMatchers("/oa/act/app/**")
                .and()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .and()
            .formLogin()
            	.loginProcessingUrl("/login_site_post")
                .failureUrl("/login?login_error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/loginSuccess")
                .loginPage("/login");
        // @formatter:on
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

}
