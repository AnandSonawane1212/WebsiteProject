package com.example.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.website.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
