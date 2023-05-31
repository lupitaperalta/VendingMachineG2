/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Scanner;

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
    
    public void run() throws VendingMachinePersistenceException {
        //DECALRED VARIABLES
        BigDecimal moneyDeposited = new BigDecimal("0");
        Product selectedProduct = null;
        String keepGoing = "yes";
        String input;
        Scanner scan = new Scanner(System.in);
        
        while(keepGoing.equals("yes")){
            
            //LOCAL VARIABLES
            boolean isEnoughMoney = false;
            
            try {
                displayWelcomeHeader();
                
                do {
                    productMenu();
                    
                    moneyDeposited = userMoneyInput(moneyDeposited);
                    
                    selectedProduct = getChosenProduct();
                    
                    isEnoughMoney = didUserPutSufficientAmountOfMoney(userAmount, selectedProduct);
                    
                    if(toExitVendingMachine(isEnoughMoney)){
                        return;
                    }
                } while (!isEnoughMoney); 
                
                displayUserMoneyInput(moneyDeposited);
                displayChangeReturnedToUser(moneyDesposited, selectedProduct);
                updateSoldProduct(selectedProduct);
                saveProductList;
                
                                
            } catch (VendingMachinePersistenceException ex){
                
                displayErrorMessage(ex.getMessage());
                
            } finally {
                
                displayFinalMessage();
                
            }
            
            view.displayUserResponse();
            keepGoing = scan.nextLine();
                        
        }
        
    } 
    

    void displayWelcomeHeader() {
        view.displayHeader();
      
    }  
    
    void productMenu() {
        
        view.displayMenuBanner();
        view.displayItemHeader();
        view.displayProduct();
        
        
    }
    //Need to Update
    BigDecimal userMoneyInput (BigDecimal moneyDeposited) {
        
         moneyDeposited = view.promptUserMoney;
         return moneyDeposited;
        
    }
    
    Product getChosenProduct() {
        
        String productId = view.promptUserProductChoice();
        
        try {
            
            Product product = service.getChosenProduct(productId);
            view.displayUserProductChoice(product);
            return product;
        } catch (VendingMachineNoItemInventoryException ex) {
            
            view.displayErrorMessage(ex.getMessage());
        }
        
    }
    
    boolean didUserPutSufficientAmountOfMoney(BigDecimal userAmount, Product product){
        try {
            
            service.checkSufficientMoneyToBuyProduct(userAmount, product);
            return;
            
        } catch (VendingMachineInsufficientFundsException ex) {
            
            displayErrorMessage(ex.getMessage());
            displayUserMoneyInput(userAmount);
            return false;
        }
    }
    
    void displayUserMoneyInput(BigDecimal amount){
        view.displayUserMoneyInput(amount);
    }
    
    void displayChangeReturnedToUser(BigDecimal amount, Product product){
        
        Change change = service.calculateChange(amount, product);
        view.displayChangeDue(change);
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
    
    void saveProudctList() throws VendingMachinePersistenceException{
        
        service.saveProductList();
    }
    
    void displayFinalMessage() {
        view.displayFinalMessage();
    }
    
                
    
 }
