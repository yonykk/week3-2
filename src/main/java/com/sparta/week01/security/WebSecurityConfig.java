package com.sparta.week01.security;

import com.sparta.week01.security.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired
    protected WebSecurityConfig(CustomAuthenticationFilter customAuthenticationFilter) {
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Override
        public void configure(HttpSecurity http) throws Exception {
            http.cors().and();
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // JWT등의 토큰 방식을 사용할 때 설정 - 스프링시큐리티가 세션을 생성하지 않을 수 있도록

            http
                    .authorizeRequests()
                    .antMatchers("/api/auth/**").authenticated()
                    .anyRequest().permitAll();
        http.addFilterBefore(customAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

            //addFilterBefore 는 UsernamePasswordAuthenticationFilter 전에 동작
            //addFilterAfter 는 UsernamePasswordAuthenticationFilter 실행 후 동작

        }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}