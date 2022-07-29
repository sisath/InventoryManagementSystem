package com.qa.ims.controllers;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest  {
    @Mock
    private Utils utils;

    @Mock
    private OrderDAO dao;

    @InjectMocks
    private OrderController controller;

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        long customerId = 1L;
        Customer customer = new Customer(customerId, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        Order created = new Order(customer);

        Mockito.when(utils.getLong()).thenReturn(customerId);
        Mockito.when(dao.create(any())).thenReturn(created);

        assertEquals(created, controller.create());
        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).create(created);
    }

    @Test
    public void testReadAll() {
        Customer customer = new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, customer, 1L));

        Mockito.when(dao.readAll()).thenReturn(orders);

        assertEquals(orders, controller.readAll());
        Mockito.verify(dao, Mockito.times(1)).readAll();
    }

	@Test
	public void testUpdate() {
        Customer customer = new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "98JH67");
		Order updated = new Order(1L, customer);

		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(utils.getString()).thenReturn("98JH67");
		Mockito.when(dao.update(any())).thenReturn(updated);

		assertEquals(updated, controller.update());
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}

    @Test
    public void testDelete() {
        long ordersId = 1L;

        Mockito.when(utils.getLong()).thenReturn(ordersId);
        Mockito.when(dao.delete(ordersId)).thenReturn(1);

        assertEquals(1L, controller.delete());
        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(ordersId);
    }
}
