package com.qa.ims;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.model.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class Menu {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customer;
	private final ProductController product;
	private final Utils utils;
	private final OrderController order;
	private final OrderProductController orderProduct;

	public Menu() {
		this.utils = new Utils();
		final CustomerDAO cDAO = new CustomerDAO();
		final ProductDAO pDAO = new ProductDAO();
		final OrderDAO oDAO = new OrderDAO();
		final OrderProductDAO oritDAO = new OrderProductDAO();
		this.orderProduct = new OrderProductController(oritDAO, utils);
		this.order = new OrderController(oDAO, utils);
		this.product = new ProductController(pDAO, utils);
		this.customer = new CustomerController(cDAO, utils);
	}

	public void menuSystem() {
		LOGGER.info(">>> Welcome to the Inventory Management System <<<");
		DBUtils.connect();

		Domain domain;
		do {
			LOGGER.info("Entity:" + "\n");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.EXIT);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
				case CUSTOMER -> active = this.customer;
				case PRODUCT -> active = this.product;
				case ORDER -> active = this.order;
				case ORDER_PRODUCT -> active = this.orderProduct;
				case EXIT -> {
					return;
				}
				default -> {
				}
			}

			LOGGER.info(() ->"What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.BACK) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
			case CREATE -> crudController.create();
			case READ -> crudController.readAll();
			case UPDATE -> crudController.update();
			case DELETE -> crudController.delete();
			case ADD_PRODUCT -> orderProduct.addProducts();
			case REMOVE_PRODUCT -> orderProduct.removeProducts();
			default -> {
			}
		}
	}
}
