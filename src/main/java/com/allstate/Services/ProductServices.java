package com.allstate.Services;


import com.allstate.Entities.Product;
import com.allstate.Repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    @Autowired
    private IProductRepository repository;

    public Product createProduct(Product p){
        return this.repository.save(p);
    }

    public Product findByNameOrStockNumber(String name, String no){
        return this.repository.findByNameOrStockNumber(name,no);
    }

    public Product updateProduct(int id, Product product){
        Product before = this.repository.findOne(id);
        before.setName(product.getName());
        before.setDescription(product.getDescription());
        before.setStockNumber(product.getStockNumber());
        before.setActualPrice(product.getActualPrice());
        before.setPerDiscount(product.getPerDiscount());
        before.setQuantity(product.getQuantity());
        before.setListPrice(product.getListPrice());
        before.setReviewCount(product.getReviewCount());
        before.setRating(product.getRating());

        return this.repository.save(before);
    }
//    public double findAvgRating(){
//        return this.repository.findAvgRating();
//    }

}
