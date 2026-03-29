package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;   // 👈 Missing import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory-api/checkStock/{productCode}/{quantity}")
    Boolean checkStock(@PathVariable("productCode") String productCode,
                       @PathVariable("quantity") int quantity);
}
