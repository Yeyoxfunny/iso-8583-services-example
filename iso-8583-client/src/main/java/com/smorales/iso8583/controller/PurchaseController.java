package com.smorales.iso8583.controller;

import com.smorales.iso8583.dto.FinancialRequestDTO;
import com.smorales.iso8583.dto.FinancialResponseDTO;
import com.smorales.iso8583.service.PurchaseService;
import com.smorales.iso8583.util.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FinancialResponseDTO> purchase(@RequestBody @Valid FinancialRequestDTO purchaseRequest) {
        LOGGER.info("Purchase Request: {}", ServerUtils.stringifyAsJson(purchaseRequest));
        FinancialResponseDTO purchaseResponse = purchaseService.purchase(purchaseRequest);
        LOGGER.info("Purchase Response: {}", ServerUtils.stringifyAsJson(purchaseResponse));
        return ResponseEntity.ok(purchaseResponse);
    }

}
