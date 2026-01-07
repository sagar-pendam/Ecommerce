package com.ecommerce.apigateway.filter;

import java.util.Collections;

import com.ecommerce.apigateway.service.JWTService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthWebFilter implements WebFilter {

    @Autowired
    private JWTService jwtService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Public endpoints
        if (path.startsWith("/auth-api/login") ||
            path.startsWith("/auth-api/register") ||
            path.startsWith("/auth-api/refresh-token")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);

        try {
            //  Full validation + parsing in one step
            Claims claims = jwtService.extractAllClaims(token);
            String username = claims.getSubject();

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authToken));

        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();

        } catch (io.jsonwebtoken.JwtException ex) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
    }

}
