package com.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.task.security.MemberAuthenticationProvider;
import com.task.security.MemberLoginFailureHandler;
import com.task.security.MemberLoginSuccessHandler;
import com.task.security.MemberLogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            AuthenticationSuccessHandler memberSuccessHandler,
            AuthenticationFailureHandler memberFailureHandler,
            LogoutSuccessHandler memberLogoutSuccessHandler,
            MemberAuthenticationProvider memberAuthenticationProvider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/member/signup", "/member/signin", "/favicon.ico", "/css/**", "/js/**").permitAll()
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/member/signin")
                        .loginProcessingUrl("/api/members/signin")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(memberSuccessHandler)
                        .failureHandler(memberFailureHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/api/members/logout")
                        .logoutSuccessHandler(memberLogoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .authenticationProvider(memberAuthenticationProvider);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler memberLoginSuccessHandler() {
        return new MemberLoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler memberFailureHandler() {
        return new MemberLoginFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler memberLogoutSuccessHandler() {
        return new MemberLogoutSuccessHandler();
    }

}
