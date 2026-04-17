package com.shoppingapp.main.controller;


import com.shoppingapp.main.dto.InventoryRequestDTO;
import com.shoppingapp.main.dto.InventoryResponseDTO;
import com.shoppingapp.main.service.InventoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService; 

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> addStock(@Valid @RequestBody InventoryRequestDTO requestDto) {
        return new ResponseEntity<>(inventoryService.createInventory(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAll() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PutMapping("/{id}/update-stock")
    public ResponseEntity<InventoryResponseDTO> updateStock(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateQuantity(id, quantity));
    }
}
