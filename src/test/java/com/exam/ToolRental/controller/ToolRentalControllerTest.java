package com.exam.ToolRental.controller;

import com.exam.ToolRental.model.RentalAgreement;
import com.exam.ToolRental.service.ToolRentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ToolRentalController.class)
public class ToolRentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolRentalService toolRentalService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    @Test
    public void testCheckout_JAKR_5Days_101PercentDiscount() throws Exception {
        String toolCode = "JAKR";
        int rentalDays = 5;
        int discountPercent = 101;
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);

        RentalAgreement rentalAgreement = new RentalAgreement();
        // Set the expected values for the rental agreement if needed

        when(toolRentalService.checkout(eq(toolCode), eq(rentalDays), eq(discountPercent), eq(checkoutDate)))
                .thenThrow(new IllegalArgumentException("Discount percentage cannot be more than 100"));

        mockMvc.perform(get("/api/rental/checkout")
                        .param("toolCode", toolCode)
                        .param("rentalDays", String.valueOf(rentalDays))
                        .param("discountPercent", String.valueOf(discountPercent))
                        .param("checkoutDate", checkoutDate.format(dateFormatter)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Discount percentage cannot be more than 100"));
    }

    @Test
    public void testCheckout_LADW_3Days_10PercentDiscount() throws Exception {
        String toolCode = "LADW";
        int rentalDays = 3;
        int discountPercent = 10;
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType("Ladder");
        rentalAgreement.setToolBrand("Werner");
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDays));
        rentalAgreement.setDailyRentalCharge(new BigDecimal("1.99"));
        rentalAgreement.setChargeDays(3);
        rentalAgreement.setPreDiscountCharge(new BigDecimal("5.97"));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(new BigDecimal("0.60"));
        rentalAgreement.setFinalCharge(new BigDecimal("5.37"));

        when(toolRentalService.checkout(eq(toolCode), eq(rentalDays), eq(discountPercent), eq(checkoutDate)))
                .thenReturn(rentalAgreement);

        mockMvc.perform(get("/api/rental/checkout")
                        .param("toolCode", toolCode)
                        .param("rentalDays", String.valueOf(rentalDays))
                        .param("discountPercent", String.valueOf(discountPercent))
                        .param("checkoutDate", checkoutDate.format(dateFormatter)))
                .andExpect(status().isOk())
                .andExpect(content().string(rentalAgreement.getFormattedText()));
    }

    @Test
    public void testCheckout_JAKD_6Days_0PercentDiscount() throws Exception {
        String toolCode = "JAKD";
        int rentalDays = 6;
        int discountPercent = 0;
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType("Jackhammer");
        rentalAgreement.setToolBrand("DeWalt");
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDays));
        rentalAgreement.setDailyRentalCharge(new BigDecimal("2.99"));
        rentalAgreement.setChargeDays(6);
        rentalAgreement.setPreDiscountCharge(new BigDecimal("17.94"));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(new BigDecimal("0.00"));
        rentalAgreement.setFinalCharge(new BigDecimal("17.94"));

        when(toolRentalService.checkout(eq(toolCode), eq(rentalDays), eq(discountPercent), eq(checkoutDate)))
                .thenReturn(rentalAgreement);

        mockMvc.perform(get("/api/rental/checkout")
                        .param("toolCode", toolCode)
                        .param("rentalDays", String.valueOf(rentalDays))
                        .param("discountPercent", String.valueOf(discountPercent))
                        .param("checkoutDate", checkoutDate.format(dateFormatter)))
                .andExpect(status().isOk())
                .andExpect(content().string(rentalAgreement.getFormattedText()));
    }

    @Test
    public void testCheckout_JAKR_9Days_0PercentDiscount() throws Exception {
        String toolCode = "JAKR";
        int rentalDays = 9;
        int discountPercent = 0;
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType("Jackhammer");
        rentalAgreement.setToolBrand("Ridgid");
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDays));
        rentalAgreement.setDailyRentalCharge(new BigDecimal("2.99"));
        rentalAgreement.setChargeDays(9);
        rentalAgreement.setPreDiscountCharge(new BigDecimal("26.91"));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(new BigDecimal("0.00"));
        rentalAgreement.setFinalCharge(new BigDecimal("26.91"));

        when(toolRentalService.checkout(eq(toolCode), eq(rentalDays), eq(discountPercent), eq(checkoutDate)))
                .thenReturn(rentalAgreement);

        mockMvc.perform(get("/api/rental/checkout")
                        .param("toolCode", toolCode)
                        .param("rentalDays", String.valueOf(rentalDays))
                        .param("discountPercent", String.valueOf(discountPercent))
                        .param("checkoutDate", checkoutDate.format(dateFormatter)))
                .andExpect(status().isOk())
                .andExpect(content().string(rentalAgreement.getFormattedText()));
    }

    @Test
    public void testCheckout_CHNS_2Days_0PercentDiscount() throws Exception {
        String toolCode = "CHNS";
        int rentalDays = 2;
        int discountPercent = 0;
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType("Chainsaw");
        rentalAgreement.setToolBrand("Stihl");
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDays));
        rentalAgreement.setDailyRentalCharge(new BigDecimal("1.49"));
        rentalAgreement.setChargeDays(2);
        rentalAgreement.setPreDiscountCharge(new BigDecimal("2.98"));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(new BigDecimal("0.00"));
        rentalAgreement.setFinalCharge(new BigDecimal("2.98"));

        when(toolRentalService.checkout(eq(toolCode), eq(rentalDays), eq(discountPercent), eq(checkoutDate)))
                .thenReturn(rentalAgreement);

        mockMvc.perform(get("/api/rental/checkout")
                        .param("toolCode", toolCode)
                        .param("rentalDays", String.valueOf(rentalDays))
                        .param("discountPercent", String.valueOf(discountPercent))
                        .param("checkoutDate", checkoutDate.format(dateFormatter)))
                .andExpect(status().isOk())
                .andExpect(content().string(rentalAgreement.getFormattedText()));
    }

}
