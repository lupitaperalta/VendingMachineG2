package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;

import java.util.List;
import java.util.Map;

public interface VendingMachineDao {

    Product addProduct(String productId, Product product);

    List<Product> getAllProducts();

    List<String> getAllProductsIds();

    Product getProduct(String productId);

    Product updateProduct(String productId, Product product);

    Product removeProduct(String productId);

    //TODO: FIX EXCEPTION
    Map<String, Product> loadProductsFromFile() throws Exception;

    void writeProductToFile() throws Exception;
}
