package com.api.middleware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.function.Function;

@Configuration
public class ApiWallConfig {
    public Function<String, Boolean> explainWallBasedApproach() {
        return requestPath -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            boolean isSupplierEndpoint = requestPath.contains("/supplier/");
            boolean isMarketEndpoint = requestPath.contains("/market/");
            
            if (isSupplierEndpoint) {
                return hasRole(auth, "SUPPLIER");
            } else if (isMarketEndpoint) {
                return hasRole(auth, "MARKET");
            }
            
            return false;
        };
    }
    
    private boolean hasRole(Authentication auth, String role) {
        if (auth == null) return false;
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }
}
