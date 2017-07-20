package com.shop.config;

import com.shop.model.service.Manager.MyUserDetailsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by 18240 on 2017/7/19.
 */

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public MyUserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_SELL') or hasRole('ROLE_BUYER') or hasRole('ROLE_SUPER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/super_admin/**").access("hasRole('ROLE_SUPER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_SUPER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .rememberMe()
                .and()
                .formLogin()
                .loginPage("/login.do").permitAll()
                .defaultSuccessUrl("/loginSuccess.do")
                .failureUrl("/loginFailed.do")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().
                antMatchers(
                        "/register.do",
                        "/js/**",
                        "/fonts/**",
                        "/css/**",
                        "/images/**",
                        "/**/favicon.ico",
                        "/index",
                        "/login.do",
                        "/",
                        "/user_register.do");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
