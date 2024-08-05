package com.exam.ToolRental.service;


import com.exam.ToolRental.model.RentalAgreement;
import com.exam.ToolRental.model.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import java.util.HashMap;
import java.util.Map;

@Service
public class ToolRentalService {
    private static final Map<String, Tool> toolInventory = new HashMap<>();

    static {
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49"), true, false, true));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner", new BigDecimal("1.99"), true, true, false));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolInventory.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99"), true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        Tool tool = toolInventory.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);

        int chargeDays = calculateChargeDays(tool, checkoutDate, rentalDays);
        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAmount = preDiscountCharge.multiply(new BigDecimal(discountPercent)).divide(new BigDecimal(100), RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType(tool.getToolType());
        rentalAgreement.setToolBrand(tool.getBrand());
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(dueDate);
        rentalAgreement.setDailyRentalCharge(tool.getDailyCharge());
        rentalAgreement.setChargeDays(chargeDays);
        rentalAgreement.setPreDiscountCharge(preDiscountCharge);
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(discountAmount);
        rentalAgreement.setFinalCharge(finalCharge);

        return rentalAgreement;
    }

    private int calculateChargeDays(Tool tool, LocalDate checkout, int rentalDays) {
        int chargeDays = 0;
        LocalDate currentDate = checkout.plusDays(1);
        LocalDate dueDate = checkout.plusDays(rentalDays);

        while (!currentDate.isAfter(dueDate)) {
            boolean isWeekend = currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY;
            boolean isHoliday = isHoliday(currentDate);

            if ((tool.isWeekdayCharge() && !isWeekend && !isHoliday) ||
                    (tool.isWeekendCharge() && isWeekend) ||
                    (tool.isHolidayCharge() && isHoliday)) {
                chargeDays++;
            }

            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }

    private boolean isHoliday(LocalDate date) {
        // Independence Day (July 4th)
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        // Labor Day (First Monday in September)
        LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}