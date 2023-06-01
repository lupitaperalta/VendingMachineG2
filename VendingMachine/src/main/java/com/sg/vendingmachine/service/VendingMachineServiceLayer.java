/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;
import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Group 2
 */
public interface VendingMachineServiceLayer {

    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException,VendingMachineNoItemInventoryException;
    public void saveProductList() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineException;
    public Product getChosenProduct(String productId) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineException;
    public void checkSufficientMoneyToBuyProduct(BigDecimal moneyIn, Product product) throws VendingMachineInsufficientFundsException;
    public Change calculateChange(BigDecimal amount, Product product);
    public void updateProductSale(Product product) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineException;
}