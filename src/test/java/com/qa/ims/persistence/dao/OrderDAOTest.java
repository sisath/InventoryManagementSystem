package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrderDAOTest {
    private final OrderDAO DAO = new OrderDAO();
    Customer customer = new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ" );

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        Order created = new Order(1L, customer, 50L);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, customer, 50L));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(1L, customer, 50L), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ordersId = 1L;
        assertEquals(new Order(ordersId, customer, 50L), DAO.read(ordersId));
    }

    @Test
    public void testUpdate() {
        final Order updated = new Order(1L, customer, 50L);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(0, DAO.delete(0));
    }

    @Test
    public void testReadException() {
        DAO.delete(0L);
        assertNull(DAO.read(0L));
    }

    @Test
    public void testReadLatestException() {
        DAO.delete(2L);
        DAO.delete(1L);
        assertNull(DAO.readLatest());
    }

    @Test
    public void testCreateException() {
        final Customer customer = new Customer(9L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        final Order order= new Order(9L, customer);
        assertNull(DAO.create(order));
    }
    @Test
    public void testUpdateException() {
        DAO.delete(1L);
        final Customer customer = new Customer(9L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        final Order order= new Order(1L, customer);
        assertNull(DAO.update(order));
    }

    @Test
    public void deleteSucceedsWhenIdExists() {
        assertEquals(1L, DAO.delete(1L));
    }

    @Test
    public void deleteFailsWhenIdDoesNotExist() {
        assertEquals(0L, DAO.delete(123L));
    }
}
