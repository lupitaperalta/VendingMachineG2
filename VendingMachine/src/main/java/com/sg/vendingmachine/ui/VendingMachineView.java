package com.sg.vendingmachine.ui;
/**
 *
 * @author Group 2
 */

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.CoinValue;
import com.sg.vendingmachine.dto.Product;

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
        io.print(product.getProductId() + "\t" + product.getProductName() + "\t\t" + product.getPrice());
    }
    
    public BigDecimal promptMoneyInput() {
        return io.readBigDecimal("Please input the amount money in dollars before choosing a beverage.");
    }
    
    public String promptUserProductChoice() {
        return io.readString("Please input the item ID for your choice of beverage.");
    }
    
    public String displayUserProductChoice(Product product){
        return io.readString("You have chosen: " + product.getProductId() + "\t" + product.getProductName() + "\t" + product.getPrice() + ".\n Press Enter to Contine.");
    }
    
    public void displayMoneyInput(BigDecimal amount) {
        
        io.print("You have deposited $" + amount);
        
    }
    
    public void displayChange(Change change) {
        
        io.print("---CHANGE DUE---");
        io.print(CoinValue.QUARTERS + ":" + change.getQuarters());
        io.print(CoinValue.DIMES + ":" + change.getDimes());
        io.print(CoinValue.NICKELS + ":" + change.getNickels());
        io.print(CoinValue.PENNIES + ":" + change.getPennies());
       
        
    }


    public void displayBalance(BigDecimal bal) {
        io.print("Current balance: $"+ bal.setScale(2, RoundingMode.HALF_UP ));
    }


    public void displayFinalMessage() {
        io.print("THANK YOU FOR USING THE VENDING MACHINE! GOODBYE!");
    }
    
    public void displayErrorMessage(String message) {
        io.print("ERROR---"+ message);
    }
    
    public boolean toExit() {
        
        String answer = io.readString("Do you want to make Exit? Yes or No").toLowerCase();
        
        return answer.contains("y");
       
    }
    
    public void displayUserResponse(){
        io.print("Do you want to make another selection? Yes or No");
        
    }
    
    
}

