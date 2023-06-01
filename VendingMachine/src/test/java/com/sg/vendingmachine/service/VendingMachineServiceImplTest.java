package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceImplTest {
    //the instance of service layer being tested
    private VendingMachineServiceLayer testService;

    //constructs new instance of ServiceImplTest class
    //initializes testService with DaoStubImpl and AuditDaoStubImpl
    public VendingMachineServiceImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao= new VendingMachineAuditDaoStubImpl();
        testService = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testLoadProductsInStock() throws VendingMachineException, VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        try {
            //ARRANGE
            BigDecimal cost = new BigDecimal("1.25");
            Product item1 = new Product("1", "Water", cost, 9);

            //expected result
            Map<String, Product> expResult = new HashMap<>();
            expResult.put("1", item1);

            //ACT
            Map<String,Product> result = testService.loadProductsInStock();

            //ASSERT
            assertEquals(expResult, result, "Test Products loaded from file the same");
        }catch(VendingMachineNoItemInventoryException | VendingMachinePersistenceException ex){
            fail("Product was valid. No exception should have been thrown");
        }
    }

    @Test
    public void testGetProduct() throws VendingMachineNoItemInventoryException, VendingMachineException, VendingMachinePersistenceException {
        //ARRANGE
        BigDecimal cost = new BigDecimal("1.25");
        Product product = new Product("1", "Water", cost, 9);

        //ACT
        Product result = testService.getChosenProduct("1");

        //ASSERT
        assertEquals(product, result, "Tests that products are equal");
        assertEquals(product.getProductName(),result.getProductName(), "Check that the products are the same");
    }

    @Test
    public void testCheckSufficientFunds() throws VendingMachineInsufficientFundsException, VendingMachineException, VendingMachineNoItemInventoryException {
        try{
            //ARRANGE
            System.out.println("checkSufficientFunds");
            BigDecimal inputAmount = new BigDecimal("3.50");
            Product result = testService.getChosenProduct("1");

            //ACT
            testService.checkSufficientMoneyToBuyProduct(inputAmount,result);
        }catch (VendingMachineInsufficientFundsException ex){
            fail("Sufficient funds to buy product. No exception should have been thrown");

        } catch (VendingMachinePersistenceException | VendingMachineNoItemInventoryException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testCalculateChange() throws VendingMachineException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        //ARRANGE
        BigDecimal amount = new BigDecimal("5");
        Product product = testService.getChosenProduct("1");

        //ACT
        Change change = testService.calculateChange(amount,product);

        //ASSERT
        assertEquals(15, change.getQuarters(), "Change should return 15 quarters");
        assertEquals(0, change.getDimes(), "Change should return 0 dimes");
        assertEquals(0, change.getNickels(), "Change should return 0 nickels");
        assertEquals(0, change.getPennies(), "Change should return 0 pennies");
    }
    @Test
    public void testUpdateItemInventory() throws VendingMachineException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        //ARRANGE
        Product product = testService.getChosenProduct("1");

        //ASSERT before sale
        assertEquals(9, product.getItemsInStock(), "Check items in stock");

        //ACT
        testService.updateProductSale(product);

        //ASSERT after sale
        Product updateItem = testService.getChosenProduct("1");
        assertEquals(8,  updateItem.getItemsInStock(), "Check products in stock now 9");
    }
}