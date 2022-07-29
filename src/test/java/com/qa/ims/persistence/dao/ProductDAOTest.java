package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductDAOTest {
    private final ProductDAO DAO =  new ProductDAO();

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        Product created = new Product(2L,10.00f, "Note", "A Note");
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(1L,10.00f, "Notebook", "Very nice notebook"));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Product(1L, 10.00f, "Notebook", "Very nice notebook"), DAO.readLatest());
    }

    @Test
    public void testRead() {
        long productId = 1L;
        assertEquals(new Product(productId, 10.00f,"Notebook", "Very nice notebook"), DAO.read(productId));
    }

    @Test
    public void testUpdate() {
        Product updated = new Product(1L, 10.00f, "Notebook", "Very nice notebook");
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
    public void testUpdateException() {
        Product product = new Product(1L, "Notebook", "1234567891123456789112345678911234567891123123");
        assertNull(DAO.update(product));
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
