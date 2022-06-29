//package com.controlaltinsert.parkshark.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().authorizeRequests().anyRequest().authenticated()
//                .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui/**",
//                "/swagger*/**",
//                "/v3/api-docs/**",
//                "/webjars/**")
//                .and()
//                .ignoring().antMatchers(HttpMethod.POST, "/customers");
//    }
//
//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(new KeycloakGrantedAuthoritiesConverter());
//        return converter;
//    }
//
//
//
//
//}
//
