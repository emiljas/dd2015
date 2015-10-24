package com.hp.devday.pietryna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@EnableWebMvc
@SpringBootApplication
public class PietrynaApp extends SpringBootServletInitializer {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PietrynaApp.class, args);
    }

    @Bean
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
                auth.jdbcAuthentication().dataSource(dataSource)
                        //select email,password,enabled from users where email = ?
                        .usersByUsernameQuery("select email, '', true from user where email = ?")
                        //select email,authority from authorities where email = ?
                        .authoritiesByUsernameQuery("select email, 'USER_ROLE' from user where email = ?");
            }

            @Override
            protected void configure(final HttpSecurity http) throws Exception {
                http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
                http.httpBasic().authenticationEntryPoint((request, response, authException) -> {
                    response.setHeader("WWW-Authenticate", "FormBased");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                });
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            }
        };
    }
}
