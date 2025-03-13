package org.library.auth.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.library.auth.jwt.service.JwtService;
import org.library.auth.security.filter.JwtFilter;
import org.library.shared.dto.RequestFailed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.HashMap;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    private final String PUBLIC_ROUTES[] = {
            "/v1/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity, CorsConfigurationSource corsConfigurationSource
    ) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                //CONFIGURAR LOS CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                //CONFIGURAR LAS ENTRADAS A LOS ENDPOINT EN GENERAL
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(PUBLIC_ROUTES).permitAll();
                    request.anyRequest().authenticated(); //LAS QUE NO ESTAN ESPECIFICADAS COMO LIBRES DEBEN AUTENTIFICARSE
                })
                //AGREGAR EL FILTRO JWT
                .addFilterBefore(new JwtFilter(userDetailsService, jwtService), UsernamePasswordAuthenticationFilter.class)
                //PERSONALIZAR LA SALIDA DE LOS MENSAJES
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                        RequestFailed requestFailed = new RequestFailed(List.of("Acceso denegado"));
                        ObjectMapper mapper = new ObjectMapper();
                        response.setStatus(403);
                        response.setHeader("Content-Type", "application/json");
                        mapper.writeValueAsString(requestFailed);
                        response.getWriter().write(mapper.writeValueAsString(requestFailed));
                    }).authenticationEntryPoint((request, response, authException) -> {
                        RequestFailed requestFailed = new RequestFailed(List.of("Acceso no autorizado"));
                        ObjectMapper mapper = new ObjectMapper();
                        response.setStatus(401);
                        response.setHeader("Content-Type", "application/json");
                        mapper.writeValueAsString(requestFailed);
                        response.getWriter().write(mapper.writeValueAsString(requestFailed));
                    });
                })
                .build();
    }


}
