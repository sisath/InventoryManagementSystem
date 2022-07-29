package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.model.Customer;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerDAO customerDAO;
	private final Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Enter a First Name: ");
		String firstName = utils.getString();
		LOGGER.info("Enter a Surname: ");
		String surname = utils.getString();
		LOGGER.info("Enter an E-Mail: ");
		String email = utils.getString();
		LOGGER.info("Enter a Post Code: ");
		String postCode = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, surname, email, postCode));
		LOGGER.info("Success!");
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("ID of Customer to Update: ");
		long customerId = utils.getLong();
		LOGGER.info("Enter a First Name: ");
		String firstName = utils.getString();
		LOGGER.info("Enter a Surname: ");
		String surname = utils.getString();
		LOGGER.info("Enter an E-Mail: ");
		String email = utils.getString();
		LOGGER.info("Enter a Post Code: ");
		String postCode = utils.getString();
		Customer customer = customerDAO.update(new Customer(customerId, firstName, surname, email, postCode));
		LOGGER.info("Success!");
		return customer;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("ID of Customer to Delete: ");
		long customerId = utils.getLong();
		return customerDAO.delete(customerId);
	}
}
