package com.devcrew.moodcode.global.config;

import com.devcrew.moodcode.global.auth.jwt.JwtAuthenticationFilter;
import com.devcrew.moodcode.global.auth.jwt.TokenProvider;
import com.devcrew.moodcode.global.auth.oauth.CustomOAuth2UserService;
import com.devcrew.moodcode.global.auth.oauth.OAuth2LoginSuccessHandler;
import com.devcrew.moodcode.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final RedisService redisService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF, FormLogin 비활성화 (JWT 환경)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 2. 세션 사용 안 함 (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. API 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**", "/login/**", "/oauth2/**").permitAll() // 로그인 관련은 누구나
                        .anyRequest().authenticated() // 그 외는 인증 필요
                )

                // 4. OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler)
                )

                // 5. JWT 필터 추가 (UsernamePasswordFilter 앞에서 실행)
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, redisService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}