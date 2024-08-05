package com.exam.ToolRental.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    public RentalAgreement() {

    }

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyRentalCharge, int chargeDays, BigDecimal preDiscountCharge, int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

//    public String getFormattedText() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
//        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
//        DecimalFormat percentFormat = new DecimalFormat("0%");
//
//        return "Tool code: " + toolCode + "\n" +
//                "Tool type: " + toolType + "\n" +
//                "Tool brand: " + toolBrand + "\n" +
//                "Rental days: " + rentalDays + "\n" +
//                "Check out date: " + dateFormat.format(checkoutDate) + "\n" +
//                "Due date: " + dateFormat.format(dueDate) + "\n" +
//                "Daily rental charge: " + currencyFormat.format(dailyRentalCharge) + "\n" +
//                "Charge days: " + chargeDays + "\n" +
//                "Pre-discount charge: " + currencyFormat.format(preDiscountCharge) + "\n" +
//                "Discount percent: " + percentFormat.format(discountPercent / 100.0) + "\n" +
//                "Discount amount: " + currencyFormat.format(discountAmount) + "\n" +
//                "Final charge: " + currencyFormat.format(finalCharge);
//    }
    public String getFormattedText() {
        return String.format("Rental Agreement:\n" +
                        "Tool code: %s\n" +
                        "Tool type: %s\n" +
                        "Tool brand: %s\n" +
                        "Rental days: %d\n" +
                        "Checkout date: %s\n" +
                        "Due date: %s\n" +
                        "Daily rental charge: $%.2f\n" +
                        "Charge days: %d\n" +
                        "Pre-discount charge: $%.2f\n" +
                        "Discount percent: %d%%\n" +
                        "Discount amount: $%.2f\n" +
                        "Final charge: $%.2f",
                toolCode, toolType, toolBrand, rentalDays, checkoutDate, dueDate,
                dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent,
                discountAmount, finalCharge);
    }
}


