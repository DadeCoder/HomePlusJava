package com.dade.server.config;

import com.dade.common.utils.LogUtil;
import com.dade.server.test.HunterUserRepository;
import com.dade.server.test.HunterUserSecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Dade on 2017/3/12.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HunterUserRepository repository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        LogUtil.info("configure auth start");
        auth.userDetailsService(new HunterUserSecurityServices(repository));
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();

        ;
    }

}
