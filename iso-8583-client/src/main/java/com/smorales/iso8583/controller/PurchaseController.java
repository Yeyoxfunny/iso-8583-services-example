package com.smorales.iso8583.controller;

import com.smorales.iso8583.dto.FinancialRequestDTO;
import com.smorales.iso8583.dto.FinancialResponseDTO;
import com.smorales.iso8583.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<FinancialResponseDTO> purchase(@RequestBody FinancialRequestDTO purchaseRequest) {
        return null;
    }

}
