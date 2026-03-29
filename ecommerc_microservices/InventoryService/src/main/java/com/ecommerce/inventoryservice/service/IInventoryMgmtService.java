package com.ecommerce.inventoryservice.service;

import java.util.List;

import com.ecommerce.inventoryservice.model.Inventory;

public interface IInventoryMgmtService {

    // Single operations
    Inventory addStock(Inventory inventory);
    Inventory updateStock(String productCode, int quantity);
    void deleteStock(String productCode);
    Inventory getStock(String productCode);

    // Bulk insert
    List<Inventory> addMultipleStocks(List<Inventory> inventories);

    // Get all
    List<Inventory> getAllStocks();

    // Existing
    boolean isInStock(String productCode, int quantity);
    boolean reserveProduct(String productCode, int quantity);
}