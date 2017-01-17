package com.allstate.Controllers;

import com.allstate.Entities.Product;
import com.allstate.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductServices service;

    @RequestMapping(value = "",  method= RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return this.service.createProduct(product);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Product findByStockNameorNumber(@RequestParam Map<String, String> query){
        return this.service.findByNameOrStockNumber(query.get("name"),query.get("stockNumber"));
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable int id,@RequestBody Product product){
        return this.service.updateProduct(id,product);
    }
//    @RequestMapping(value = "/avgRating" , method = RequestMethod.GET)
//    public double findAvgRating(){
//        return this.service.findAvgRating();
//    }

}
