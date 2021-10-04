package com.example.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.website.entities.User;


public interface UserRepo extends JpaRepository<User, Long>{
}
