package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct,Long> {
    Optional<BaseProduct> findByCategoryCodeAndItemCodeAndKindCodeAndClassCodeAndGradeCode
            (String categoryCode,String itemCode,String kindCode,String classCode,String gradeCode);
}
