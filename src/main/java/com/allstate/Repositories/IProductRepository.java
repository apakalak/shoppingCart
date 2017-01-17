package com.allstate.Repositories;

import com.allstate.Entities.Product;
import org.springframework.data.repository.CrudRepository;


public interface IProductRepository extends CrudRepository<Product,Integer>{
     Product findByNameOrStockNumber(String name, String stockNumber);
//     double findAvgRating();
}
