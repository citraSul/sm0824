package com.exam.ToolRental.service;

import static org.junit.jupiter.api.Assertions.*;

import com.exam.ToolRental.model.RentalAgreement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ToolRentalServiceTest {

    private static ToolRentalService toolRentalService;

    @BeforeAll
    public static void setup() {
        toolRentalService = new ToolRentalService();
    }

    private Date parseDate(LocalDate date) throws ParseException {
        return new SimpleDateFormat("MM/dd/yy").parse(String.valueOf(date));
    }

    @Test
    public void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            toolRentalService.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        });
    }

    @Test
    public void testLadderWith10PercentDiscount() throws ParseException {
        RentalAgreement agreement = toolRentalService.checkout("LADW", 3, 10,LocalDate.of(20,7,2));
        assertEquals("LADW", agreement.getToolCode());
        assertEquals("Ladder", agreement.getToolType());
        assertEquals("Werner", agreement.getToolBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(20,7,2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(20,7,5), agreement.getDueDate());
        assertEquals(new BigDecimal("1.99"), agreement.getDailyRentalCharge());
        assertEquals(2, agreement.getChargeDays());
        assertEquals(new BigDecimal("3.98"), agreement.getPreDiscountCharge());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.40"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("3.58"), agreement.getFinalCharge());
    }

    @Test
    public void testChainsawWith25PercentDiscount() throws ParseException {
        RentalAgreement agreement = toolRentalService.checkout("CHNS", 5, 25, LocalDate.of(2015,7,2));
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals("Chainsaw", agreement.getToolType());
        assertEquals("Stihl", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015,7,2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015,7,7), agreement.getDueDate());
        assertEquals(new BigDecimal("1.49"), agreement.getDailyRentalCharge());
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal("4.47"), agreement.getPreDiscountCharge());
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge());
    }

    @Test
    public void testJackhammerNoDiscount() throws ParseException {
        RentalAgreement agreement = toolRentalService.checkout("JAKD", 6, 0, LocalDate.of(2015,9,3));
        assertEquals("JAKD", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("DeWalt", agreement.getToolBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015,9,3), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015,9,9), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal("8.97"), agreement.getPreDiscountCharge());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("8.97"), agreement.getFinalCharge());
    }

    @Test
    public void testJackhammerNoDiscountLongRental() throws ParseException {
        RentalAgreement agreement = toolRentalService.checkout("JAKR", 9, 0, LocalDate.of(2015,7,2));
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015,7,2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015,7,11), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
        assertEquals(5, agreement.getChargeDays());
        assertEquals(new BigDecimal("14.95"), agreement.getPreDiscountCharge());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("14.95"), agreement.getFinalCharge());
    }

    @Test
    public void testJackhammer50PercentDiscount() throws ParseException {
        RentalAgreement agreement = toolRentalService.checkout("JAKR", 4, 50, LocalDate.of(2020,7,2));
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020,7,2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020,7,6), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyRentalCharge());
        assertEquals(1, agreement.getChargeDays());
        assertEquals(new BigDecimal("2.99"), agreement.getPreDiscountCharge());
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(new BigDecimal("1.50"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("1.49"), agreement.getFinalCharge());
    }
}

