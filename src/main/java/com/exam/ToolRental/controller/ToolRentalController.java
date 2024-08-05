package com.exam.ToolRental.controller;

import com.exam.ToolRental.model.RentalAgreement;
import com.exam.ToolRental.service.ToolRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/rental")
public class ToolRentalController {

    private static final Logger logger = LoggerFactory.getLogger(ToolRentalController.class);

    @Autowired
    private ToolRentalService toolRentalService;

    @GetMapping("/checkout")
    public ResponseEntity<String> checkout(
            @RequestParam("toolCode") String toolCode,
            @RequestParam("rentalDays") int rentalDays,
            @RequestParam("discountPercent") int discountPercent,
            @RequestParam("checkoutDate") @DateTimeFormat(pattern = "MM/dd/yy") LocalDate checkoutDate) {
        try {
            logger.info("Received parameters - toolCode: {}, rentalDays: {}, discountPercent: {}, checkoutDate: {}",
                    toolCode, rentalDays, discountPercent, checkoutDate);
            RentalAgreement rentalAgreement = toolRentalService.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            return ResponseEntity.ok(rentalAgreement.getFormattedText());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
