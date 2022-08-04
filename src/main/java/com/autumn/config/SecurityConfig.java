package com.autumn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    //TODO: Agregar las urls a las que pueden acceder por rol
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/users/**", "/settings/**", "/usuario")
                    .hasAuthority("IBP")
                    .antMatchers("/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                   // .usernameParameter("mail")
                    .permitAll()
                .and()
                //  .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                //  .and()
                    .logout().permitAll();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
