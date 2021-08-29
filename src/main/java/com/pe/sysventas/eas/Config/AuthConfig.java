/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Deary
 */
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter{
    
    //crear usuarios y roles
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}1234")
                    .roles("ADMIN")
                .and()
                .withUser("cliente")
                    .password("{noop}1234")
                    .roles("CLIENTE");
    }*/
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public  BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    //autorizar a vistas
    @Override
    protected void configure(HttpSecurity htpp) throws Exception
    {
        
        htpp.authorizeRequests()
                .antMatchers("/home").hasAnyAuthority("ADMIN","CLIENTE")
                .antMatchers("/").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticated")
                    .defaultSuccessUrl("/home", true)
                .and()
                    .exceptionHandling().accessDeniedPage("/errors/403")
                ;
    }
    
}
