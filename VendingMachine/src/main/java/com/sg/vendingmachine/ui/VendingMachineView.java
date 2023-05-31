package com.sg.vendingmachine.ui;
/**
 *
 * @author Group 2
 */

import com.sal.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineView {
    private UserIO io = new UserIOConsoleImpl();
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayHeader() {
        io.print("WELCOME TO THE VENDING MACHINE");
    }
    public void displayMenuBanner() {
        io.print("=== Vending Machine Menu ===");
    }
    public void displayItemHeader() {
        io.print("No\tItem\t\tCost");
        io.print("---------------------");
    }
    public void displayProduct(Product product) {
        io.print(product.getProductId() + "\t" + product.getProductName() + "\t" + product.getPrice());
    }
    public BigDecimal getMoneyInput() {
        return io.readBigDecimal("Please input the amount money in dollars before choosing a beverage.");
    }
    public String promptUserProductChoice() {
        return io.readString("Please input the item ID for your choice of beverage.");
    }
    public String displayUserProductChoice(Product product){
        return io.readString("You have chosen: " + product.getProductId() + "\t" + product.getProductName() + "\t" + product.getPrice() + ".");
    }


    public void displayBalance(BigDecimal bal) {
        io.print("Current balance:"+bal.setScale(2, RoundingMode.HALF_UP));
    }


    public void displayFinalMessage() {
        io.print("THANK YOU FOR USING THE VENDING MACHINE! GOODBYE!");
    }
}

