package com.air.careerassistant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsServiceImpl userDetailsService;
        @Bean
        public PasswordEncoder passwordEncoder(){
            BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
            return bCryptPasswordEncoder;
        }
        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
        @Override
        protected void configure(final HttpSecurity http) throws Exception{
            http
                    .cors().disable() //whitelist pages
                    .csrf().disable() //cross site resource forgery
                    .authorizeRequests()//all sites until AND are connected
                    .antMatchers("/").permitAll()
                    .antMatchers( "/signIn", "/login").permitAll()
                    .anyRequest().authenticated() //forces login
                    .and()
                    .formLogin()//settings about Login
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID"); // this creates a get route of /logout
        }
    }

