package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CoffeeEntity {
    @Id
    private final String id;
    private String name;

}
