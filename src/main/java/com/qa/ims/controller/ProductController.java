package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductController implements CrudController<Product> {
    public static final Logger LOGGER = LogManager.getLogger();

    private final ProductDAO productDAO;
    private final Utils utils;

    public ProductController(ProductDAO productDAO, Utils utils) {
        super();
        this.productDAO = productDAO;
        this.utils = utils;
    }

    /**
     * Reads all Product to the logger
     */
    @Override
    public List<Product> readAll() {
        List<Product> product = productDAO.readAll();
        for (Product product1 : product) {
            LOGGER.info(product1);
        }
        return product;
    }

    /**
     * Creates a Product by taking in user input
     */
    @Override
    public Product create() {
        LOGGER.info("Enter a Product Price £(XX.XX): ");
        double price = utils.getDouble();
        LOGGER.info("Enter a Product Name: ");
        String productName = utils.getString();
        LOGGER.info("Enter a Description");
        String productDescription = utils.getString();
        Product product = productDAO.create(new Product(price, productName, productDescription));
        LOGGER.info("Success!");
        return product;
    }

    /**
     * Updates an existing Products by taking in user input
     */
    @Override
    public Product update() {
        LOGGER.info("ID of Product to Update: ");
        long productId = utils.getLong();
        LOGGER.info("Enter a Price £(XX.XX): ");
        double price = utils.getDouble();
        LOGGER.info("Enter a Product Name: ");
        String productName = utils.getString();
        LOGGER.info("Enter a Description");
        String productDescription = utils.getString();
        Product product = productDAO.update(new Product(productId, price, productName, productDescription));
        LOGGER.info("Success!");
        return product;
    }

    /**
     * Deletes an existing Products by the id of the Products
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("ID of Product to Delete: ");
        long productId = utils.getLong();
        return productDAO.delete(productId);
    }
}
