package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.util.List;
import java.util.Map;

public interface VendingMachineDao {

    Product addProduct(String productId, Product product) throws VendingMachineException;

    List<Product> getAllProducts() throws VendingMachineException;

    List<String> getAllProductsIds() throws VendingMachineException;

    Product getProduct(String productId) throws VendingMachineException;

    Product updateProduct(String productId, Product product) throws VendingMachineException;

    Product removeProduct(String productId) throws VendingMachineException;

        Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException;

    void writeProductToFile() throws VendingMachinePersistenceException, VendingMachineException;
}
