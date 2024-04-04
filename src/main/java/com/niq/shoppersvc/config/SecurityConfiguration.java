package com.niq.shoppersvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> {
                    auth.anyRequest().authenticated();
        });
        httpSecurity.sessionManagement(
                session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }
        );
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().sameOrigin();
        return httpSecurity.build();
    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        var sheik = User.withUsername("sheik1")
                .password("test123")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("DATA", "ECOMM")
                .build();

        var syed = User.withUsername("syed1")
                .password("test123")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("ECOMM")
                .build();
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(sheik);
        jdbcUserDetailsManager.createUser(syed);

        return jdbcUserDetailsManager;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
