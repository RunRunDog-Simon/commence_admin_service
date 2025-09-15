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
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // 如需 CORS，就留著
                .authorizeHttpRequests(auth -> auth
                        // Swagger
                        .requestMatchers(
                                "/swagger-ui.html", "/swagger-ui/**",
                                "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**"
                        ).permitAll()

                        // 讓前端的預檢 OPTIONS 通過（保險起見）
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                        // 很重要：錯誤頁面與常見靜態資源
                        .requestMatchers("/error", "/", "/favicon.ico").permitAll()

                        // 你目前開發中的 API
                        .requestMatchers(
                                "/users/**", "/segments/**", "/user-segments/**",
                                "/posters/**", "/categories/**"   // ← categories 補成 /** 比較保險
                        ).permitAll()

                        // 其它仍要驗證
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {}); // 開發期暫用 Basic
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
