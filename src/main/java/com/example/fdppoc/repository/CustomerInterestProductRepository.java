package com.example.fdppoc.repository;

import com.example.fdppoc.entity.CustomerInterestProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInterestProductRepository extends JpaRepository<CustomerInterestProduct,Long> {
}
