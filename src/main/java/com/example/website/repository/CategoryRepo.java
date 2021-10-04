package com.example.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.website.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
