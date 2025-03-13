package org.library.auth.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.library.auth.jwt.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token si viene en la cabecera
        String token  = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")) {
            //Sustraigo el token
            token = token.substring(7);
            //Valido el token
            if(jwtService.validateToken(token)){
                String username = jwtService.getSubject(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //Cargar al contexto
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities())
                );
            }
        }
        filterChain.doFilter(request, response);
    }

}
