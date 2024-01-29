package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.InnerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerCategoryRepository extends JpaRepository<InnerCategory,Long> {
}
