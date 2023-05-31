package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;

import java.util.List;
import java.util.Map;

public class VendingMachineDaoFileImpl implements VendingMachineDao{
    private final String INVENTORY_FILE;

    public static final String DELIMITER = "::";


    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryFile) {
        INVENTORY_FILE = inventoryFile;
    }


    @Override
    public Product addProduct(String productId, Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getAllProductsIds() {
        return null;
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return null;
    }

    @Override
    public Product removeProduct(String productId) {
        return null;
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws Exception {
        return null;
    }

    @Override
    public void writeProductToFile() throws Exception {

    }
}
