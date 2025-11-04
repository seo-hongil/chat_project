package com.project.chatproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // "/" 이하 모든 경로 허용 (임시 설정)
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(form -> form
                        // 기본 로그인 페이지 대신 커스텀 페이지 사용
                        .loginPage("/chatlogin")
                        .loginProcessingUrl("/login") // 로그인 처리 URL
                        .defaultSuccessUrl("/")       // 로그인 성공 후 이동 URL
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")       // 로그아웃 요청 URL
                        .logoutSuccessUrl("/")      // 로그아웃 성공 후 이동 URL
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
