package com.ecommerce.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.service.IInventoryMgmtService;



@RestController
@RequestMapping("/inventory-api")
public class InventoryController {

    @Autowired
    private IInventoryMgmtService service;

    //  Add single stock
    @PostMapping("/add")
    public ResponseEntity<Inventory> addStock(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(service.addStock(inventory));
    }

    //  Bulk insert
    @PostMapping("/bulk")
    public ResponseEntity<List<Inventory>> addMultipleStocks(@RequestBody List<Inventory> inventories) {
        return ResponseEntity.ok(service.addMultipleStocks(inventories));
    }

    //  Update
    @PutMapping("/update/{productCode}/{quantity}")
    public ResponseEntity<Inventory> updateStock(
            @PathVariable("productCode") String productCode,
            @PathVariable("quantity") int quantity) {

        return ResponseEntity.ok(service.updateStock(productCode, quantity));
    }

    //  Delete
    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<String> deleteStock(
            @PathVariable("productCode") String productCode) {

        service.deleteStock(productCode);
        return ResponseEntity.ok("Deleted successfully");
    }

    //  Get single
    @GetMapping("/{productCode}")
    public ResponseEntity<Inventory> getStock(
            @PathVariable("productCode") String productCode) {

        return ResponseEntity.ok(service.getStock(productCode));
    }

    //  Get all
    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllStocks() {
        return ResponseEntity.ok(service.getAllStocks());
    }

   
    @GetMapping("/check/{productCode}/{quantity}")
    public ResponseEntity<Boolean> checkStock(
            @PathVariable("productCode") String productCode,
            @PathVariable("quantity") int quantity) {

        return ResponseEntity.ok(service.isInStock(productCode, quantity));
    }

    
    @PostMapping("/reserve/{productCode}/{quantity}")
    public ResponseEntity<String> reserve(
            @PathVariable("productCode") String productCode,
            @PathVariable("quantity") int quantity) {

        boolean status = service.reserveProduct(productCode, quantity);
        return status ? ResponseEntity.ok("Reserved")
                      : ResponseEntity.badRequest().body("Insufficient stock");
    }
}