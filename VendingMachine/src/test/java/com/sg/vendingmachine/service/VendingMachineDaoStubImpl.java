package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a stub implementation of the VendingMachineDao interface.
 * It provides hardcoded data and empty method implementations.
 * This stub is used during testing to simulate the data access functionality.
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    public Product onlyProduct;
    public VendingMachineDaoStubImpl() {
        onlyProduct = new Product("1", "Water", BigDecimal.valueOf(1.25), 9);
    }

    @Override
    public Product addProduct(String productId, Product product) throws VendingMachineException {
        if (productId.equals(onlyProduct.getProductId())){
        }else{
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() throws VendingMachineException {
        List<Product> productList = new ArrayList<>();
        productList.add(onlyProduct);
        return productList;
    }

    @Override
    public List<String> getAllProductsIds() throws VendingMachineException {
        List<String> productIdsList = new ArrayList<>();
        productIdsList.add(onlyProduct.getProductId());
        return productIdsList;
    }

    @Override
    public Product getProduct(String productId) throws VendingMachineException {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        }else{
            return null;
        }
    }

    @Override
    public Product updateProduct(String productId, Product product) throws VendingMachineException {
        if (productId.equals(onlyProduct.getProductId())){
        }else{
        }
        return product;
    }

    @Override
    public Product removeProduct(String productId) throws VendingMachineException {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        }else{
            return null;
        }
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        Map<String,Product> itemMap = new HashMap<>();
        itemMap.put("1", onlyProduct);
        return itemMap;
    }

    @Override
    public void writeProductToFile() throws VendingMachineException {

    }
}
