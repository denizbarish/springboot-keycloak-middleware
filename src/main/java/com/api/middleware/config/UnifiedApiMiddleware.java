package com.api.middleware.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnifiedApiMiddleware extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(UnifiedApiMiddleware.class);
    private static final String API_PATH_PREFIX = "/api/";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        try {
            if (isApiRequest(requestURI)) {
                processApiRequest(request, response, filterChain);
            } else {
                processNonApiRequest(request, response, filterChain);
            }
        } catch (Exception e) {
            logger.error("İstek işlenirken hata oluştu: {} {}", method, requestURI, e);
            throw e;
        }
    }

    private boolean isApiRequest(String requestURI) {
        return requestURI != null && requestURI.startsWith(API_PATH_PREFIX);
    }
    
    private void processApiRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        filterChain.doFilter(request, response);
    }
    
    private void processNonApiRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        filterChain.doFilter(request, response);
    }
}