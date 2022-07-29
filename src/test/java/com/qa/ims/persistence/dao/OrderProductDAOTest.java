package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.persistence.model.OrderProduct;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrderProductDAOTest {
    private final OrderProductDAO DAO = new OrderProductDAO();
    Customer customer = new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
    Product product = new Product(1L, 10.00f, "Notebook", "Very nice notebook");
    Order order = new Order(1L, customer, 50);
    Order updatedOrder = new Order(1L, customer, 60);

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        OrderProduct created = new OrderProduct(2L, product, updatedOrder,1L);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<OrderProduct> expected = new ArrayList<>();
        expected.add(new OrderProduct(1L, product, order,5L));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }

    @Test
    public void testReadException() {
        DAO.delete(1L);
        assertNull(DAO.read(1L));
    }

    @Test
    public void testReadLatestException() {
        DAO.delete(2L);
        DAO.delete(1L);
        assertNull(DAO.readLatest());
    }

    @Test
    public void testDeleteException() {
        DAO.delete(1L);
        assertEquals(0,DAO.delete(1L));
    }

    @Test
    public void testDeleteByOrderIdAndProductId() {
        assertEquals(1, DAO.deleteByOrderIdAndProductId(1, 1));
    }

    @Test
    public void testDeleteByOrderIdAndProductIdWhenDoesNotExist() {
        assertEquals(0, DAO.deleteByOrderIdAndProductId(123, 123));
    }

    @Test
    public void testAddProducts() {
        OrderProduct created = new OrderProduct(2L, product, updatedOrder,1L);
        assertEquals(created, DAO.addProduct(1L, 1L, 1));
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
