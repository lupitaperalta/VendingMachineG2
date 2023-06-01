package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendingMachineDaoFileImplTest {
    //The path to the test file used for testing
    String testFile = "test.txt";

    //Creates an instance of the DAO implementation to be tested
    VendingMachineDao testDao = new VendingMachineDaoFileImpl(testFile);

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void testAddProduct() throws Exception, VendingMachineException {
        //ARRANGE: creates two product instances and an expected result map
        Product product1 = new Product("1", "Water", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 10);
        Product product2 = new Product("2", "Chips", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), 10);
        Map<String, Product> expResult = new HashMap<>();
        expResult.put(product1.getProductId(), product1);
        expResult.put(product2.getProductId(), product2);

        //ACT: adds the products to the DAO and retrieves the resulting map
        testDao.addProduct(product1.getProductId(),product1);
        testDao.addProduct(product2.getProductId(), product2);
        Map<String, Product> result = testDao.loadProductsFromFile();

        //ASSERT: checks the expected and actual results
        assertNotNull(result, "The map should not be null");
        assertEquals(expResult, result, "Tests to check that the products were properly added to the map");
        assertTrue(result.containsKey(product1.getProductId()),"The map should contain product1");
        assertTrue(result.containsKey(product2.getProductId()), "The map should contain product2");
        assertEquals(2, result.size(),"The map should have two products");
    }

    @Test
    void testGetAllProducts() throws Exception, VendingMachineException {
        //ARRANGE: creates two product instances, adds them to DAO, and creates expected result list
        Product product1 = new Product("1", "Water", BigDecimal.valueOf(1.50), 10);
        Product product2 = new Product("2", "Chips", BigDecimal.valueOf(3.50), 10);
        testDao.addProduct(product1.getProductId(),product1);
        testDao.addProduct(product2.getProductId(),product2);

        //ACT: retrieves all products from DAO
        List<Product> result = testDao.getAllProducts();
        List<Product> expResult = new ArrayList<>();
        expResult.add(product1);
        expResult.add(product2);

        //ASSERT: checks expected and actual results
        assertEquals(expResult, result, "Test get all products from file and return list");
        assertNotNull(result, "The list of products is not null");
        assertTrue(result.contains(product1), "The list should contain Water");
        assertTrue(result.contains(product2), "The list should contain Chips");
        assertEquals(2, result.size(), "The list should contain 2 products");
    }

    @Test
    void testGetAllProductsIds() throws Exception, VendingMachineException {
        //ARRANGE: creates two product instances, adds them to DAO, and creates expected result list
        Product product1 = new Product("1", "Water", BigDecimal.valueOf(1.50), 10);
        Product product2 = new Product("2", "Chips", BigDecimal.valueOf(3.50), 10);
        testDao.addProduct(product1.getProductId(), product1);
        testDao.addProduct(product2.getProductId(), product2);

        //ACT: retrieves all product IDs from DAO
        List<String> result = testDao.getAllProductsIds();
        List<String> expResult = new ArrayList<>();
        expResult.add(product1.getProductId());
        expResult.add(product2.getProductId());

        //ASSERT: checks expected and actual results
        assertNotNull(result, "The list of products should not be null");
        assertEquals(expResult, result,"Test get all product ids from file and return list");
        assertEquals(2, result.size(), "The list should contain two products ids");
        assertTrue(result.contains(product1.getProductId()),"The list should contain product:1");
        assertTrue(result.contains(product2.getProductId()), "The list should contain product:2");
    }

    @Test
    void testGetProduct() throws Exception, VendingMachineException {
        //ARRANGE: creates two product instances and adds to the DAO
        Product product1 = new Product("1", "Water", BigDecimal.valueOf(1.50), 10);
        Product product2 = new Product("2", "Chips", BigDecimal.valueOf(3.50), 10);
        testDao.addProduct(product1.getProductId(),product1);
        testDao.addProduct(product2.getProductId(), product2);

        //ACT: retrieves products by their IDs from DAO
        Product resultItem1 = testDao.getProduct(product1.getProductId());
        Product resultItem2 = testDao.getProduct(product2.getProductId());

        //ASSERT: checks expected and actual results
        assertNotNull(resultItem1, "resultProduct1 should not be null");
        assertNotNull(resultItem2, "resultProduct2 should not be null");
        assertEquals(product1, resultItem1, "Test that product1 is correctly returned");
        assertEquals(product2, resultItem2, "Test that product2 is correctly returned");
    }

    @Test
    void testUpdateProduct() throws Exception, VendingMachineException {
        //ARRANGE: creates two product instances, adds them to DAO, and creates expected result map
        Map<String, Product> expResult = new HashMap<>();
        Product product1 = new Product("1", "Water", BigDecimal.valueOf(1.50), 0);
        Product product2 = new Product("2", "Chips", BigDecimal.valueOf(3.50), 0);
        expResult.put(product1.getProductId(), product1);
        expResult.put(product2.getProductId(), product2);

        //ACT: creates new product instances, loads existing products from DAO, and update them
        Product newProduct1 = new Product("1", "Water", BigDecimal.valueOf(1.50), 0);
        Product newProduct2 = new Product("2", "Chips", BigDecimal.valueOf(3.50), 0);
        Map<String,Product> resultMap = testDao.loadProductsFromFile();
        testDao.updateProduct("1", newProduct1);
        testDao.updateProduct("2", newProduct2);

        //ASSERT: checks expected and actual results
        assertNotNull(resultMap, "resultMap should not be null");
        assertEquals(expResult, resultMap, "Tests that products can be updated");
        assertEquals(2, resultMap.size(), "resultMap should have two products");
    }

    @Test
    void testLoadProductsFromFile() throws Exception {
        //ARRANGE: creates two product instances representing the expected contents of the file
        Product product1 = new Product("1", "Water", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 10);
        Product product2 = new Product("2", "Chips", new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP), 10);

        //ACT: loads products from file using DAO
        Map<String, Product> result = testDao.loadProductsFromFile();
        Map<String, Product> expResult = new HashMap<>();
        expResult.put("1", product1);
        expResult.put("2", product2);

        //ASSERT: checks expected and actual results
        assertEquals(expResult, result, "Test products loaded from file.");
    }
}
