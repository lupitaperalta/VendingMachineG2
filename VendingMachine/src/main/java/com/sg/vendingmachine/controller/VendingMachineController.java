/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korinna
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service =service;
    }
    
    public void run() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        //DECALRED VARIABLES
        BigDecimal moneyDeposited = new BigDecimal("0");
        Product selectedProduct = null;
        String keepGoing = "yes";
        String input = "";
        Scanner scan = new Scanner(System.in);
        
        while(keepGoing.equals("yes")){
            
            //LOCAL VARIABLES
            boolean isEnoughMoney = false;
            
            try {
                displayHeader();
                
                do {
                    productMenu();
                    
                    moneyDeposited = userMoneyInput(moneyDeposited);
                    
                    selectedProduct = getChosenProduct();
                    
                    isEnoughMoney = didUserPutSufficientAmountOfMoney(moneyDeposited, selectedProduct);
                    
                    if(toExitVendingMachine(isEnoughMoney)){
                        return;
                    }
                    
                } while (!isEnoughMoney); 
 
                displayUserMoneyInput(moneyDeposited);
                displayChangeReturnedToUser(moneyDeposited, selectedProduct);
                updateSoldProduct(selectedProduct);
                saveProudctList();
                
                                
            } catch (VendingMachinePersistenceException ex){
                
                displayErrorMessage(ex.getMessage());
                
            } finally {
                
                displayFinalMessage();
                
            }
            
            view.displayUserResponse();
            keepGoing = scan.nextLine();
                        
        }
        
    } 
    
    void displayHeader() {
        view.displayHeader();
        view.displayMenuBanner();
        view.displayItemHeader();
        
    }  
    
    //Need to Update
    void productMenu() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        
        
        Map<String, Product> availableProducts = service.loadProductsInStock();
        
        for (String key: availableProducts.keySet()) {
            view.displayProduct(availableProducts.get(key));
        }
        
    }
    
 
    BigDecimal userMoneyInput (BigDecimal amount) {
        
        BigDecimal total = amount.add(view.promptMoneyInput());
        
        return total;
        
        
    }
    
    Product getChosenProduct() throws VendingMachinePersistenceException {
       
        String productId = view.promptUserProductChoice();
        
        try {
            
            Product product = service.getChosenProduct(productId);
            view.displayUserProductChoice(product);
            
            return product;
            
        } catch (VendingMachineNoItemInventoryException ex) {
            
            view.displayErrorMessage(ex.getMessage());       
        }
        
        return null;
    }
    
    boolean didUserPutSufficientAmountOfMoney(BigDecimal userAmount, Product product){
        try {
            
           service.checkSufficientMoneyToBuyProduct(userAmount,product);
            
            return true;
        } catch (VendingMachineInsufficientFundsException ex) {
            
            displayErrorMessage(ex.getMessage());
            displayUserMoneyInput(userAmount);
            return false;
        }
        
        
    }
    
    void displayUserMoneyInput(BigDecimal amount){
        view.displyMoneyInput(amount);
    }
    
    void displayChangeReturnedToUser(BigDecimal amount, Product product){
        
        Change change = service.calculateChange(amount, product);
        view.displayChange(change);
    }
    
    boolean toExitVendingMachine(boolean isEnoughMoney) {
        if(isEnoughMoney) {
            return false;
        } else {
            return view.toExit();
        }
    }
    
    void displayErrorMessage (String message) {
        view.displayErrorMessage(message);
    }
    
    void updateSoldProduct (Product product) throws VendingMachinePersistenceException {
        try {
            
            service.updateProductSale(product);
           
        } catch(VendingMachineNoItemInventoryException ex) {
            
             throw new VendingMachinePersistenceException (ex.getMessage());
        }
    }
    
    void saveProudctList() throws VendingMachinePersistenceException {
        
        try {
            service.saveProductList();
        } catch (VendingMachineNoItemInventoryException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void displayFinalMessage() {
        view.displayFinalMessage();
    }
    
                
    
 }
