package com.qa.ims.controllers;

import com.qa.ims.controller.OrderProductController;
import com.qa.ims.persistence.dao.OrderProductDAO;
import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.persistence.model.OrderProduct;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderProductControllerTest {
    @Mock
    private Utils utils;

    @Mock
    private OrderProductDAO dao;

    @InjectMocks
    private OrderProductController controller;

    private final Customer customer = new Customer(1L,"Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
    private final Product product = new Product(1L, 10L, "Notebook", "Very nice notebook");
    private final Order order = new Order(1L, customer, 60L);

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

	@Test
	public void testCreate() {
        long quantity = 1L;
		OrderProduct created = new OrderProduct(2L, product, order, quantity);

		Mockito.when(utils.getLong()).thenReturn(1L);

		assertEquals(created, controller.create());
		Mockito.verify(utils, Mockito.times(4)).getLong();
	}

    @Test
    public void testReadAll() {
        List<OrderProduct> orderProduct = new ArrayList<>();
        orderProduct.add(new OrderProduct(product, order, 1L));

        Mockito.when(dao.readAll()).thenReturn(orderProduct);

        assertEquals(orderProduct, controller.readAll());

        Mockito.verify(dao, Mockito.times(1)).readAll();
    }

	@Test
	public void testUpdate() {
		OrderProduct updated = new OrderProduct(2L, product, order, 1L);

		Mockito.when(utils.getLong()).thenReturn(1L, 1L);

		assertEquals(updated, controller.update());

		Mockito.verify(utils, Mockito.times(5)).getLong();
	}

    @Test
    public void testDelete() {
        long orderProductId = 1L;

        Mockito.when(utils.getLong()).thenReturn(orderProductId);
        Mockito.when(dao.delete(orderProductId)).thenReturn(1);

        assertEquals(1L, controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(orderProductId);
    }

    @Test
    public void testAddProducts() {
        long quantity = 1L;
        OrderProduct created = new OrderProduct(2L, product, order, quantity);

        Mockito.when(utils.getLong()).thenReturn(1L);

        assertEquals(created, controller.addProducts());
        Mockito.verify(utils, Mockito.times(3)).getLong();
    }

    @Test
    public void testRemoveProducts() {
        long orderProductId = 1L;

        Mockito.when(utils.getLong()).thenReturn(orderProductId);

        assertEquals(1L, controller.removeProducts());
    }
}
