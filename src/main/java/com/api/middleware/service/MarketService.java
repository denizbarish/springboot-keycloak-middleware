package com.api.middleware.service;

import com.api.middleware.model.Market;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MarketService {

    public List<Market> getAllMarkets() {
        return Arrays.asList(
            new Market(1L, "Ebrar Market", "19 Mayıs Mahallesi Sultan Sokak", "Bakkal"),
            new Market(2L, "A101", "19 Mayıs Mahallesi Sultan Sokak", "Zincir Market"),
            new Market(3L, "Rossman", "19 Mayıs Mahallesi Sultan Sokak", "Kozmetik Market")
        );
    }
}
