/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author Group 2
 */
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class Product {
    private String productId;
    private String productName;
    private BigDecimal price;
    private int itemsInStock;
    
    private final String DELIMITER = "::";
    
    public Product(String productId, String productName, BigDecimal price, int itemsInStock){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.itemsInStock = itemsInStock;      
    }
    
    public Product(String productAsText) throws VendingMachinePersistenceException{
        try{
            String[] fields = productAsText.split(DELIMITER);
            this.productId = fields[0];
            this.productName = fields[1];
            this.price = new BigDecimal(fields[2]);
            this.itemsInStock = Integer.parseInt(fields[3]);
        } catch (PatternSyntaxException ex){
            throw new VendingMachinePersistenceException(ex.getMessage());
        }catch (NullPointerException | NumberFormatException ex){
            throw new VendingMachinePersistenceException(ex.getMessage());
        }
        
    }
    
    public String getProductId(){return productId;}
    public void setProductId(String productId){this.productId = productId;}
    
    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName = productName;}
    
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal price){this.price= price;}
    
    public int getItemsInStock(){return itemsInStock;}
    public void setItemsInStock(int itemsInStock){this.itemsInStock = itemsInStock;}
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.productId);
        hash = 79 * hash + Objects.hashCode(this.productName);
        hash = 79 * hash + Objects.hashCode(this.price);
        hash = 79 * hash + this.itemsInStock;
        return hash;
    }
    
    @Override 
    public String toString(){
        return "Product{productId=" + productId + ", productName=" + productName + ", price=" + price + ", itemsInStock=" + itemsInStock;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){return true;}
        if (o == null){return false;}
        
        if (getClass() != o.getClass()){
            return false;
        }
        final Product productB = (Product) o;
        if (this.itemsInStock != productB.itemsInStock){return false;}
        if (!Objects.equals(this.productId, productB.productId)){
            return false;
        }
        if (!Objects.equals(this.productName, productB.productName)){
            return false;
        }
        if (!Objects.equals(this.price, productB.price)){
            return false;
        }
        return true;
    }
    
    public String marshallProductAsText(){
        return productId + DELIMITER + productName + DELIMITER + price + DELIMITER + itemsInStock;
    }
    
}
