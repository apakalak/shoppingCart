package com.allstate.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
//@NamedQuery(name="findAvgRating" ,  query ="Select avg(\"rating\") from products")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique=true, nullable = false)
    private String name;

    @Column(unique=true, nullable = false)
    private String stockNumber;

    private String description;
    private int rating;
    private int reviewCount;
    private float listPrice;
    private int perDiscount;
    private float actualPrice;
    private int quantity;
    private boolean isAdult;

    @Version
    private int version;

}
