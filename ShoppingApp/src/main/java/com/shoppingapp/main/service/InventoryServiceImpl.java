package com.shoppingapp.main.service;


import com.shoppingapp.main.entity.*;
import com.shoppingapp.main.dto.InventoryRequestDTO;
import com.shoppingapp.main.dto.InventoryResponseDTO;
import com.shoppingapp.main.repository.InventoryRepository;
import com.shoppingapp.main.repository.ProductRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InventoryResponseDTO createInventory(InventoryRequestDTO requestDto) {
        Inventory inventory = modelMapper.map(requestDto, Inventory.class);
        
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setProduct(product);
        
        Inventory saved = inventoryRepository.save(inventory);
        return modelMapper.map(saved, InventoryResponseDTO.class);
    }

    @Override
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inv -> modelMapper.map(inv, InventoryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDTO updateQuantity(Long id, Integer newQuantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory record not found"));
        inventory.setStockQuantity(newQuantity);
        
        return modelMapper.map(inventoryRepository.save(inventory), InventoryResponseDTO.class);
    }

    @Override
    public boolean validateAndReduceStock(Long inventoryId, Integer quantity) {
        Inventory inv = inventoryRepository.findByProductProductId(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product id"));
        
        if (inv.getStockQuantity() >= quantity) {
            inv.setStockQuantity(inv.getStockQuantity() - quantity); 
            inventoryRepository.save(inv);
            return true;
        }
        return false; 
    }
}