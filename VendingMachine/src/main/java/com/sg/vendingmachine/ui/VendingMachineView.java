package com.sg.vendingmachine.ui;
/**
 *
 * @author Group 2
 */

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.CoinValue;

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

    public void displayProductHeader() {
        io.print("No\tItem\t\tCost");
        io.print("---------------------");
    }

    public void displayProduct(Product product) {

        io.print(product.getProductId() + "\t" + product.getProductName());
    }

    public BigDecimal displayUserMoneyInput() {
        return io.readBigDecimal("Please input the amount money in dollars before choosing a beverage.");
    }

    public String promptUserProductChoice() {
        return io.readString("Please input the item ID for your choice of beverage.");
    }

    public String displayUserProductChoice(Product product) {
        return io.readString("You have chosen: " + product.getProductId() + "\t" + product.getProductName() + "\t" + product.getPrice() + ".");
    }

    public void displayChangeDue(Change change) {
        io.print("Here is your change: ");
        io.print(CoinValue.QUARTERS + " : " + change.getQuarters());
        io.print(CoinValue.DIMES + " : " + change.getDimes());
        io.print(CoinValue.NICKELS + " : " + change.getNickels());
        io.print(CoinValue.PENNIES + " : " + change.getPennies());
    }

    public void displayBalance(BigDecimal bal) {
        io.print("Current balance:" + bal.setScale(2, RoundingMode.HALF_UP));
    }

    public void displayErrorMessage(String message) {
        io.print(message + '\n');
        io.readString("Please hit enter to continue.");
    }

    public boolean toExit() {
        String answer = io.readString("Do you want to keep using the Vending Machine? (yes/no)").toLowerCase();
        if (answer.startsWith("y")) {
            return false;
        } else {
            return true;
        }
    }
    public void displayFinalMessage() {

        io.print("THANK YOU FOR USING THE VENDING MACHINE! GOODBYE!");
    }
}

