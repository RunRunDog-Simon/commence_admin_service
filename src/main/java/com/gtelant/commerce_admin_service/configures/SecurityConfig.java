package com.gtelant.commerce_admin_service.configures;


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

    //Chat GPT 提供解法
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    //Chat GPT 提供解法
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Swagger 用 POST/PUT 會被 CSRF 擋，開發期先關
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 允許 Swagger
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // 目前開發中的 API（可依需要調整）
                        .requestMatchers("/users/**", "/segments/**", "/user-segments/**").permitAll()
                        // 其它仍需驗證
                        .anyRequest().authenticated()
                )
                // 簡單一點，用 HTTP Basic（不顯示登入頁）
                .httpBasic(basic -> {});
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
