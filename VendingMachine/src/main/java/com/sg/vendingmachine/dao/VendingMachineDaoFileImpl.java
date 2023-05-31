package com.sg.vendingmachine.dao;
import com.sg.vendingmachine.dto.Product;
import java.io.*;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao{
    private final String INVENTORY_FILE;

    public static final String DELIMITER = "::";

    private Map<String, Product> products = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryFile) {
        INVENTORY_FILE = inventoryFile;
    }

    @Override
    public Product addProduct(String productId, Product product) {
        Product prevProduct = products.put(productId, product);
        return prevProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public List<String> getAllProductsIds() {
        return new ArrayList<String>(products.keySet());
    }

    @Override
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return products.replace(productId, product);
    }

    @Override
    public Product removeProduct(String productId) {
        Product removedProduct = products.remove(productId);
        return removedProduct;
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws Exception {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch(FileNotFoundException e) {
            //TODO: VENDINGMACHINEPERSISTENCEEXCEPTION
            throw new FileNotFoundException("-_- Could not load data to memory");
        }
        String currentLine;
        Product currentProduct;

        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = new Product(currentLine);
            products.put(currentProduct.getProductId(), currentProduct);
        }
        scanner.close();
        return products;
    };

    @Override
    public void writeProductToFile() throws Exception {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new FileNotFoundException("Could not save product data");
        }
        String productAsText;
        List<Product> productList = this.getAllProducts();
        for(Product currentProduct : productList) {
            productAsText = currentProduct.marshallProductAsText();
            out.println(productAsText);
            out.flush();
        }
        out.close();
    }
}