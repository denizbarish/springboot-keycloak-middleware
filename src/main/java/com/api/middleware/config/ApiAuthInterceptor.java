package com.api.middleware.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        
        if (requestURI.startsWith("/api/")) {
            
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            if (auth == null || !auth.isAuthenticated()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Yetkisiz Erişim\",\"message\":\"Hatalı token girildi.\"}");
                return false;
            }
            
            String sourceHeader = request.getHeader("Source");
            
            if (sourceHeader == null || sourceHeader.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Argüman Hatası\",\"message\":\"Kullanıcı rolü belirtilmemiş.\"}");
                return false;
            }
            
            if (!sourceHeader.equals("Supplier") && !sourceHeader.equals("Market")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Argüman Hatası\",\"message\":\"Kullanıcı rolü 'Supplier' veya 'Market' olmalıdır.\"}");
                return false;
            }
            
            boolean isSupplierEndpoint = requestURI.contains("/supplier/");
            boolean isMarketEndpoint = requestURI.contains("/market/");
            
            if (isSupplierEndpoint && !sourceHeader.equals("Supplier")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Hata\",\"message\":\"Supplier paneline sadece Supplier rolü erişebilir\"}");
                return false;
            }
            
            if (isMarketEndpoint && !sourceHeader.equals("Market")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Hata\",\"message\":\"Market paneline sadece Market rolü erişebilir\"}");
                return false;
            }
        }
        
        return true;
    }
}