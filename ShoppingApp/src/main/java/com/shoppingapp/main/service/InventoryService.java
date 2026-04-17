package com.shoppingapp.main.Service;


import com.shoppingapp.main.dto.InventoryRequestDTO;
import com.shoppingapp.main.dto.InventoryResponseDTO;
import java.util.List;

public interface InventoryService {
    InventoryResponseDTO createInventory(InventoryRequestDTO requestDto);
    List<InventoryResponseDTO> getAllInventory();
    InventoryResponseDTO updateQuantity(Long id, Integer newQuantity);
    boolean validateAndReduceStock(Long inventoryId, Integer quantity);
}