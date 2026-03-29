package com.ecommerce.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;



//@Service
//public class IInventoryMgmtServiceImple implements IInventoryMgmtService {
//	private static final Logger log = LoggerFactory.getLogger(IInventoryMgmtServiceImple.class);
//	@Autowired
//	private InventoryRepository repository;
//	@Override
//	public boolean isInStock(String productCode, int quantity) {
//		log.info("Checking stock for productCode={} with required quantity={}", productCode, quantity);
//		// TODO Auto-generated method stub
//		return repository.findByProductCode(productCode).map(inventory -> inventory.getQuantity() >= quantity).orElse(false);
//	}
//	
//	@Transactional
//	public boolean reserveProduct(String productCode, int quantity) {
//		 log.info("Reserving product {} with quantity={}", productCode, quantity);
//        Optional<Inventory> inventoryOpt = repository.findByProductCode(productCode);
//        if (inventoryOpt.isPresent()) {
//            Inventory inventory = inventoryOpt.get();
//            if (inventory.getQuantity() >= quantity) {
//                inventory.setQuantity(inventory.getQuantity() - quantity);
//                repository.save(inventory);
//                log.info("Product {} reserved successfully. Remaining quantity={}", productCode, inventory.getQuantity());
//                return true;
//            }
//        }
//        log.warn("Product {} not found in inventory", productCode);
//        return false;
//    }
//
//}

@Service
@Transactional
public class IInventoryMgmtServiceImple implements IInventoryMgmtService {

    @Autowired
    private InventoryRepository repository;

    // ✅ Add single stock
    @Override
    public Inventory addStock(Inventory inventory) {
        return repository.save(inventory);
    }

    // ✅ Bulk insert
    @Override
    public List<Inventory> addMultipleStocks(List<Inventory> inventories) {
        return repository.saveAll(inventories);
    }

    // ✅ Update stock
    @Override
    public Inventory updateStock(String productCode, int quantity) {
        Inventory inventory = repository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        inventory.setQuantity(quantity);
        return repository.save(inventory);
    }

    // ✅ Delete stock
    @Override
    public void deleteStock(String productCode) {
        Inventory inventory = repository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        repository.delete(inventory);
    }

    // ✅ Get single stock
    @Override
    public Inventory getStock(String productCode) {
        return repository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ✅ Get all stocks
    @Override
    public List<Inventory> getAllStocks() {
        return repository.findAll();
    }

    // ✅ Check stock
    @Override
    public boolean isInStock(String productCode, int quantity) {
        return repository.findByProductCode(productCode)
                .map(inv -> inv.getQuantity() >= quantity)
                .orElse(false);
    }

    // ✅ Reserve stock
    @Override
    public boolean reserveProduct(String productCode, int quantity) {
        Inventory inventory = repository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getQuantity() >= quantity) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            repository.save(inventory);
            return true;
        }
        return false;
    }

	
}
