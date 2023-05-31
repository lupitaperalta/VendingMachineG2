/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author sarah
 */
public interface VendingMachineServiceLayer {


    public Map<String, Product> loadInStock throws VendingMachineException,VendingMachineItemInventoryException,VendingMachinePersistenceException;
    public void saveProductList() throws VendingMachinePersistenceException, VendingMachineException;
    public Product getProduct(String productId) throws VendingMachineException,VendingMachineItemInventoryException;
    public void checkSufficientFunds(BigDecimal moneyIn, Product product) throws VendingMachineInsufficientFundsException;
    public Change calculateChange(BigDecimal amount, Product product);
    public void updateItemInventory(Product product) throws VendingMachinePersistenceException, VendingMachineItemInventoryException, VendingMachineException;



}