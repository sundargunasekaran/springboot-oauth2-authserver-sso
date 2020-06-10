package com.springboot.oauthconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.service.CustomAuthenticationProvider;



@Configuration
@Order(1)
public class OauthSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${user.oauth.user.username}")
    private String username;

    @Value("${user.oauth.user.password}")
    private String password;
    
    @Autowired
	private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/oauth/authorize**", "/login**", "/home**" ,"/error**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
                .inMemoryAuthentication()
                .withUser(username).password(passwordEncoder().encode(password)).roles("USER");*/
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
