package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.CustomerSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSearchHistoryRepository extends JpaRepository<CustomerSearchHistory,Long>  {
}
