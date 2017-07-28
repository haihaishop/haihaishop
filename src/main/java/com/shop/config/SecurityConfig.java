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
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

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
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_SELL') or hasRole('ROLE_BUYER') or hasRole('ROLE_SUPER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/super_admin/**").access("hasRole('ROLE_SUPER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_SUPER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .rememberMe()

                .tokenValiditySeconds(1209600)
                .key("user")
                .userDetailsService(userDetailsService)

                .and()
                .formLogin()
                .loginPage("/login.do").permitAll()
                .loginProcessingUrl("/login_form.do")
                .defaultSuccessUrl("/loginSuccess.do")
                .failureUrl("/loginFailed.do")
                .and()
                .logout()
                .logoutUrl("/logout.do")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
        .and()
        .eraseCredentials(false);

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
                        "/user_register.do",
                        "/themes/**",
                        "/buyer_home_page.do/**",
                        "/goods_detail.do/**",
                        "/search/**",
                        "/shop/**");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
