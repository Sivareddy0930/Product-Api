package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.Model.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	Product getBypid(Integer Pid);
	
	 @Modifying
	 @Transactional
	void deleteBypid(Integer Pid);
}
