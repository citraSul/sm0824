# Tool Rental Application

## Overview

This project is a simple tool rental application for a store that rents out big tools. The application supports checking out tools, generating rental agreements, and applying discounts.

## Features

- Rent tools for a specified number of days
- Different daily rental charges for different tool types
- Free rental days on weekends or holidays
- Apply discounts to rental charges
- Generate rental agreements with detailed rental information

## Tools Available for Rental

| Tool Code | Tool Type    | Brand     |
|-----------|--------------|-----------|
| CHNS      | Chainsaw     | Stihl     |
| LADW      | Ladder       | Werner    |
| JAKD      | Jackhammer   | DeWalt    |
| JAKR      | Jackhammer   | Ridgid    |

## Daily Charges

| Tool Type  | Daily Charge | Weekday Charge | Weekend Charge | Holiday Charge |
|------------|--------------|----------------|----------------|----------------|
| Ladder     | $1.99        | Yes            | Yes            | No             |
| Chainsaw   | $1.49        | Yes            | No             | Yes            |
| Jackhammer | $2.99        | Yes            | No             | No             |

## Holidays

- Independence Day (July 4th) - Observed on the closest weekday if it falls on a weekend
- Labor Day - First Monday in September

## API Endpoints

### Checkout Tool

#### Request

**GET** `/api/rentals/checkout`

**Parameters:**

- `toolCode` (String) - The code of the tool to be rented.
- `rentalDays` (int) - The number of days the tool will be rented.
- `discountPercent` (int) - The discount percentage to be applied.
- `checkoutDate` (LocalDate) - The date when the tool is checked out (format: `MM/DD/YY`).

#### Response

**200 OK**

Tool type: Jackhammer
Tool brand: DeWalt
Rental days: 6
Checkout date: 2015-09-03
Due date: 2015-09-09
Daily rental charge: $2.99
Charge days: 3
Pre-discount charge: $8.97
Discount percent: 0%
Discount amount: $0.00
Final charge: $8.97
