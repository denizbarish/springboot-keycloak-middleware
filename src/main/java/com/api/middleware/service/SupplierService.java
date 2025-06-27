package com.api.middleware.service;

import com.api.middleware.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {

    public List<Supplier> getAllSuppliers() {
        return Arrays.asList(
            new Supplier(1L, "ÜLKER", "bilgi@ulker.com", "Çikolata, Bisküvi, Gıda"),
            new Supplier(2L, "ETİ", "bilgi@eti.com", "Unlu Mamuller, Bisküvi"),
            new Supplier(3L, "NESTLE", "bilgi@nestle.com", "Süt Ürünleri, Çikolata")
        );
    }
}
