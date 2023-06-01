package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Group2
 *
 * */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao=dao;
        this.auditDao=auditDao;
    }

    @Override
    public Map<String, Product> loadProductsInStock() throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        Map<String, Product> productsInStock = new HashMap<>();
        //lambda function to filter items that are in stock and items that are not
        for (Product p : dao.loadProductsFromFile().values()){
            if (p.getItemsInStock() < 1){
                auditDao.writeAuditEntry("Product id: " + p.getProductId());
            } else{
                productsInStock.put(p.getProductId(), p);
            }
        }
        return productsInStock;
    }

    @Override
    public void saveProductList() throws VendingMachinePersistenceException, VendingMachineException {
        dao.writeProductToFile();
        auditDao.writeAuditEntry("Product list saved to file");
    }

    @Override
     public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException, VendingMachineException {
        validateProductInStock(productId);
        return dao.getProduct(productId);
    }

    @Override
    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException {
        if(inputAmount.compareTo(product.getPrice())<0){
            throw new VendingMachineInsufficientFundsException("Error: Insufficient funds to purchase: "+ product.getProductName());
        }
    }

    @Override
    public Change calculateChange(BigDecimal amount, Product product) {
        BigDecimal change = amount.subtract(product.getPrice()).multiply(new BigDecimal("100"));
        return new Change(change);
    }

    @Override
    public void updateProductSale(Product product) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineException {
        if (product.getItemsInStock()>0){
            product.setItemsInStock(product.getItemsInStock()-1);
        }else{
            throw new VendingMachineNoItemInventoryException("Error: Selected product is not in stock");
        }
        dao.updateProduct(product.getProductId(), product);
        auditDao.writeAuditEntry("Product ID: "+ product.getProductId()+" is updated");
    }

    private void validateProductInStock(String productId) throws VendingMachineNoItemInventoryException, VendingMachineException {
        List<String>ids = dao.getAllProductsIds();
        Product product = dao.getProduct(productId);
        if(!ids.contains(productId)||product.getItemsInStock()<1){
            throw new VendingMachineNoItemInventoryException("Error: Selected product is not in stock");
        }
    }
}
