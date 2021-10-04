package com.example.website.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.website.entities.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Long>{

	public List<Purchase> findAllByOrderByDateOfPurchaseAsc();
	public List<Purchase> findAllByOrderByCategoryNameAsc();
}