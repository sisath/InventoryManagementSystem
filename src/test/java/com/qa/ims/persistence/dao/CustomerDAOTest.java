package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.model.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

    private final CustomerDAO DAO = new CustomerDAO();

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        Customer created = new Customer(2L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ"));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ"), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long customer_id = 1L;
        assertEquals(new Customer(customer_id, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ"), DAO.read(customer_id));
    }

    @Test
    public void testUpdate() {
        final Customer updated = new Customer(1L, "Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
        assertEquals(updated, DAO.update(updated));
    }

    @Test
    public void deleteSucceedsWhenIdExists() {
        assertEquals(1L, DAO.delete(1L));
    }

    @Test
    public void deleteFailsWhenIdDoesNotExist() {
        assertEquals(0L, DAO.delete(123L));
    }

    @Test
    public void testReadException() {
        DAO.delete(1L);
        assertNull(DAO.read(1L));
    }
}
