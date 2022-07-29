package com.qa.ims.controllers;

import com.qa.ims.controller.ProductController;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.Utils;
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
public class ProductControllerTest {

    @Mock
    private Utils utils;

    @Mock
    private ProductDAO dao;

    @InjectMocks
    private ProductController controller;

    @Test
    public void testCreate() {
        double price= 10.00;
        String productName= "Hat";
        String productDescription = "Hat";
        Product created = new Product(price, productName, productDescription);

        Mockito.when(utils.getDouble()).thenReturn(price);
        Mockito.when(utils.getString()).thenReturn(productName);
        Mockito.when(utils.getString()).thenReturn(productDescription);
        Mockito.when(dao.create(created)).thenReturn(created);

        assertEquals(created, controller.create());

        Mockito.verify(utils, Mockito.times(1)).getDouble();
        Mockito.verify(utils, Mockito.times(2)).getString();
        Mockito.verify(dao, Mockito.times(1)).create(created);
    }

    @Test
    public void testReadAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Note", "Hat"));
        Mockito.when(dao.readAll()).thenReturn(products);
        assertEquals(products, controller.readAll());
        Mockito.verify(dao, Mockito.times(1)).readAll();
    }

    @Test
    public void testUpdate() {
        Product updated = new Product(1L, 10.00, "Updated", "Updated");

        Mockito.when(utils.getLong()).thenReturn(1L);
        Mockito.when(utils.getDouble()).thenReturn(updated.getPrice());
        Mockito.when(utils.getString()).thenReturn(updated.getProductName());
        Mockito.when(utils.getString()).thenReturn(updated.getProductDescription());
        Mockito.when(dao.update(updated)).thenReturn(updated);

        assertEquals(updated, controller.update());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(utils, Mockito.times(2)).getString();
        Mockito.verify(dao, Mockito.times(1)).update(updated);
    }

    @Test
    public void testDelete() {
        final long productId = 1L;

        Mockito.when(utils.getLong()).thenReturn(productId);
        Mockito.when(dao.delete(productId)).thenReturn(1);

        assertEquals(1L, controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(productId);
    }
}
