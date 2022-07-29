package com.qa.ims.controller;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderProductDAO;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.persistence.model.OrderProduct;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import java.util.List;

import org.apache.logging.log4j.Logger;


public class OrderProductController implements CrudController<OrderProduct> {
    public static final Logger LOGGER = LogManager.getLogger();
    private final OrderProductDAO orderProductDao;
    private final Utils utils;

    public OrderProductController(OrderProductDAO orderProductDao, Utils utils) {
        super();
        this.orderProductDao = orderProductDao;
        this.utils = utils;
    }

    /**
     * Reads all OrderProduct records to the logger
     */
    @Override
    public List<OrderProduct> readAll() {
        List<OrderProduct> orderProduct = orderProductDao.readAll();
        for (OrderProduct order : orderProduct) {
            LOGGER.info(order);
        }
        return orderProduct;
    }

    /**
     * Creates an OrderProduct by taking in user input
     */
    @Override
    public OrderProduct create() {
        LOGGER.info("Enter a Customer ID: ");
        long customersID = utils.getLong();
        LOGGER.info("Enter a Product ID: ");
        long productID = utils.getLong();
        LOGGER.info("Enter an Order ID: ");
        long orderID = utils.getLong();
        LOGGER.info("Enter the Quantity: ");
        long quantity = utils.getLong();
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.read(customersID);
        Product product = productDAO.read(productID);

        Order order = new Order(orderID, customer);

        OrderProduct orderProduct = orderProductDAO.create(new OrderProduct(product, order, quantity));
        LOGGER.info("Success!");
        return orderProduct;
    }

    /**
     * Update not possible as not in project scope
     */
    @Override
    public OrderProduct update() {
        LOGGER.info("ID of Order Product to Update: ");
        long orderProductId = utils.getLong();
        LOGGER.info("Enter a Customer ID: ");
        long customersID = utils.getLong();
        LOGGER.info("Enter a Product ID: ");
        long productID = utils.getLong();
        LOGGER.info("Enter an Order ID: ");
        long orderID = utils.getLong();
        LOGGER.info("Enter the Quantity: ");
        long quantity = utils.getLong();
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.read(customersID);
        Product product = productDAO.read(productID);
        Order order = new Order(orderID, customer);
        OrderProduct orderProduct = orderProductDAO.create(new OrderProduct(orderProductId, product, order, quantity));
        LOGGER.info("Success!");
        return orderProduct;
    }

    public OrderProduct addProducts() {
        LOGGER.info("ID of Order Id: ");
        long orderId = utils.getLong();
        LOGGER.info("Enter a Product ID: ");
        long productId = utils.getLong();
        LOGGER.info("Enter the Quantity: ");
        long quantity = utils.getLong();
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        OrderProduct orderProduct = orderProductDAO.addProduct(orderId, productId, quantity);
        LOGGER.info("Success!");
        return orderProduct;
    }

    public int removeProducts() {
        LOGGER.info("ID of Order Id: ");
        long orderId = utils.getLong();
        LOGGER.info("Enter a Product ID to remove: ");
        long productID = utils.getLong();
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        int result = orderProductDAO.deleteByOrderIdAndProductId(orderId, productID);
        if (result == 0) {
            LOGGER.info("Failed!");
        } else {
            LOGGER.info("Success!");
        }
        return result;
    }

    /**
     * Deletes an existing Orders by the id of the Orders
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("ID of Order to Delete: ");
        long orderProductId = utils.getLong();
        return orderProductDao.delete(orderProductId);
    }
}
