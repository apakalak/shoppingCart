package com.allstate.Controllers;

import com.allstate.Entities.Product;
import com.allstate.Services.ProductServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServices services;

    private Product before;


    @Before
    public void setUp() throws Exception{
        before = new Product();
        before.setId(1);
        before.setName("Headset");
        before.setDescription("Headset by sony");
        before.setStockNumber("ABC-124");
        before.setActualPrice(90);
        before.setPerDiscount(10);
        before.setQuantity(20);
        before.setListPrice(100);
        before.setReviewCount(10);
        before.setRating(2);
        before.setVersion(1);
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        given(this.services.createProduct(Mockito.any(Product.class))).willReturn(before);

        MockHttpServletRequestBuilder request =  post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"name\" : \"Headset\",\n" +
                        "\t\"stockNumber\" : \"ABC-124\",\n" +
                        "\t\"listPrice\" : 100,\n" +
                        "\t\"description\" : \"Headset by sony\",\n" +
                        "\t\"quantity\" : 20,\n" +
                        "\t\"actualPrice\" : 90,\n" +
                        "\t\"perDiscount\" :10,\n" +
                        "\t\"reviewCount\" :10,\n" +
                        "\t\"rating\":2\n" +
                        "}");

        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Headset")))
                .andExpect(jsonPath("$.stockNumber",is("ABC-124")))
                .andExpect(jsonPath("$.listPrice",is(100.0)))
                .andExpect(jsonPath("$.description",is("Headset by sony")))
                .andExpect(jsonPath("$.quantity",is(20)));
    }

    @Test
    public void shouldFetchProductsBYNameOrStockNumber() throws Exception{
        MockHttpServletRequestBuilder request =  get("/products/search?name=Headset&stockNumber=");

        given(this.services.findByNameOrStockNumber("Headset","")).willReturn(before);

        this.mvc.perform(request).andExpect(status().isOk())
                    .andExpect(jsonPath("$.stockNumber",is("ABC-124")))
                    .andExpect(jsonPath("$.listPrice",is(100.0)))
                    .andExpect(jsonPath("$.description",is("Headset by sony")))
                    .andExpect(jsonPath("$.quantity",is(20)));


    }

    @Test
    public void shouldUpdateProduct() throws Exception{
        given(this.services.updateProduct(anyInt(), Mockito.any(Product.class))).willReturn(before);

        MockHttpServletRequestBuilder request =  put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"name\" : \"Headset\",\n" +
                        "\t\"stockNumber\" : \"ABC-124\",\n" +
                        "\t\"listPrice\" : 100,\n" +
                        "\t\"description\" : \"Headset by sony\",\n" +
                        "\t\"quantity\" : 20,\n" +
                        "\t\"actualPrice\" : 90,\n" +
                        "\t\"perDiscount\" :10,\n" +
                        "\t\"reviewCount\" :10,\n" +
                        "\t\"rating\":2\n" +
                        "}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version", is(1)));
    }

//    @Test
//    public void findAvgRating() throws Exception{
//        //given(this.services.findAvgRating()).willReturn(4);
//
//        MockHttpServletRequestBuilder request =  get("/products/avgRating");
//
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.avgRating", is(4)));
//    }
}