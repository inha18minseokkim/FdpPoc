package com.example.fdppoc.repository;

import com.example.fdppoc.entity.CustomerSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSearchHistoryRepository extends JpaRepository<CustomerSearchHistory,Long> {
}
