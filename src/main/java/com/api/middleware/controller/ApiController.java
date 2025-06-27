package com.api.middleware.controller;

import com.api.middleware.model.Market;
import com.api.middleware.model.Supplier;
import com.api.middleware.service.MarketService;
import com.api.middleware.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final SupplierService supplierService;
    private final MarketService marketService;

    @Autowired
    public ApiController(SupplierService supplierService, MarketService marketService) {
        this.supplierService = supplierService;
        this.marketService = marketService;
    }    

    @GetMapping("/supplier/listall")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<?> getAllSuppliers(jakarta.servlet.http.HttpServletRequest request) {
        String source = request.getHeader("Source");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String tokenType = "Keycloak JWT";
        
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        
        Map<String, Object> response = Map.of(
            "source", source,
            "auth", Map.of("username", username, "tokenType", tokenType, "roles", auth.getAuthorities().toString()),
            "data", suppliers
        );
        
        return ResponseEntity.ok(response);
    }    

    @GetMapping("/market/listall")
    @PreAuthorize("hasRole('MARKET')")
    public ResponseEntity<?> getAllMarkets(jakarta.servlet.http.HttpServletRequest request) {
        String source = request.getHeader("Source");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String tokenType = "Keycloak JWT";
        
        List<Market> markets = marketService.getAllMarkets();
        
        Map<String, Object> response = Map.of(
            "source", source,
            "auth", Map.of("username", username, "tokenType", tokenType, "roles", auth.getAuthorities().toString()),
            "data", markets
        );
        
        return ResponseEntity.ok(response);
    }
}
