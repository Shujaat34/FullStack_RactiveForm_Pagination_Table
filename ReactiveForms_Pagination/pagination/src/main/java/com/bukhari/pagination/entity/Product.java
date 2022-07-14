package com.bukhari.pagination.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private String category;
    private String durability;
    private String description;
    private Long price;
    private String comment;
    private Date date;

    public Product( String name, String category,String durability, String description, Long price, String comment, Date date) {
        this.name = name;
        this.category = category;
        this.durability = durability;
        this.description = description;
        this.price = price;
        this.comment = comment;
        this.date = date;
    }
}
