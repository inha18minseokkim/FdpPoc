package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerCategoryRepository extends JpaRepository<InnerCategory,Long> {
}
