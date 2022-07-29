package com.qa.ims.controller;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderController implements CrudController<Order> {
    public static final Logger LOGGER = LogManager.getLogger();
    private final OrderDAO orderDAO;
    private final Utils utils;
    private final CustomerDAO customerDAO = new CustomerDAO();


    public OrderController(OrderDAO orderDAO, Utils utils) {
        super();
        this.orderDAO = orderDAO;
        this.utils = utils;
    }

    /**
     * Reads all orders to the logger
     */
    @Override
    public List<Order> readAll() {
        List<Order> order = orderDAO.readAll();
        for (Order order1 : order) {
            LOGGER.info(order1);
        }
        return order;
    }

    /**
     * Creates an order by taking in user input
     */
    @Override
    public Order create() {
        LOGGER.info("Enter a Customer ID: ");
        long customerId = utils.getLong();
        Customer customer = customerDAO.read(customerId);
        Order order = orderDAO.create(new Order(customer));
        LOGGER.info("Success!");
        return order;
    }

    /**
     * Updates an existing order by taking in user input
     */
    @Override
    public Order update() {
        LOGGER.info("ID of Order to Update: ");
        long orderId = utils.getLong();
        LOGGER.info("Enter a Customer ID: ");
        long customerId = utils.getLong();
        LOGGER.info("Enter a New Customer Post Code: ");
        String newCustomerPostCode = utils.getString();
        Customer customer = customerDAO.read(customerId);
        Customer updatedCustomer = new Customer(customerId, customer.getFirstName(),
                customer.getSurname(), customer.getEmail(), newCustomerPostCode);
        Order order = orderDAO.update(new Order(orderId, updatedCustomer));
        LOGGER.info("Success!");
        return order;
    }

    /**
     * Deletes an existing order by the id of the Orders
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("ID of Order to Delete: ");
        long orderId = utils.getLong();
        return orderDAO.delete(orderId);
    }
}
