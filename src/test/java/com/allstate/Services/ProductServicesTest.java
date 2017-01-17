package com.allstate.Services;

import com.allstate.Entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class ProductServicesTest {
    @Autowired
    private ProductServices service;

    private Product before;


    @Before
    public void setUp() throws Exception{
        before = new Product();
        before.setName("Headset");
        before.setDescription("Headset by sony");
        before.setStockNumber("ABC-124");
        before.setActualPrice(90);
        before.setPerDiscount(10);
        before.setQuantity(20);
        before.setListPrice(100);
        before.setReviewCount(10);
        before.setRating(2);
    }

    @Test
    public void create_createProduct() throws  Exception{
        Product after = this.service.createProduct(before);
        assertEquals(1, after.getId());
        assertEquals("Headset", after.getName());
        assertEquals("Headset by sony", after.getDescription());
        assertEquals("ABC-124", after.getStockNumber());
        assertEquals(90, after.getActualPrice(),0.1);
        assertEquals(10, after.getPerDiscount());
        assertEquals(100, after.getListPrice(),0.1);
        assertEquals(10, after.getReviewCount());
        assertEquals(2, after.getRating());
        assertEquals(20, after.getQuantity());

    }

    @Test
    public void throwExOnDuplicateEntry_createProduct() throws  Exception{
        this.service.createProduct(before);
        this.service.createProduct(before);
        //assertEquals(this.service.findAll().count,1);
    }

    @Test
    public void should_fetchProductBasedOnNameorStockno() throws Exception{
        this.service.createProduct(before);
        Product after = this.service.findByNameOrStockNumber("Headset","ABC-124");
        assertEquals(1, after.getId());
        assertEquals("Headset", after.getName());
        assertEquals("Headset by sony", after.getDescription());
        assertEquals("ABC-124", after.getStockNumber());
        assertEquals(90, after.getActualPrice(),0.1);
        assertEquals(10, after.getPerDiscount());
        assertEquals(100, after.getListPrice(),0.1);
        assertEquals(10, after.getReviewCount());
        assertEquals(2, after.getRating());
        assertEquals(20, after.getQuantity());
    }

    @Test
    public void shouldUpdatetheProduct() throws Exception{

        this.service.createProduct(before);

        Product updated = new Product();
        updated.setName("Earplugs");
        updated.setDescription("Earplugs by sony");
        updated.setStockNumber("ABC-124");
        updated.setActualPrice(90);
        updated.setPerDiscount(10);
        updated.setQuantity(20);
        updated.setListPrice(100);
        updated.setReviewCount(10);
        updated.setRating(2);

        Product after = this.service.updateProduct(1,updated);

        assertEquals(1, after.getId());
        assertEquals("Earplugs", after.getName());
        assertEquals("Earplugs by sony", after.getDescription());
        assertEquals("ABC-124", after.getStockNumber());
        assertEquals(90, after.getActualPrice(),0.1);
        assertEquals(10, after.getPerDiscount());
        assertEquals(100, after.getListPrice(),0.1);
        assertEquals(10, after.getReviewCount());
        assertEquals(2, after.getRating());
        assertEquals(20, after.getQuantity());
        assertEquals(1, after.getVersion());

    }

}