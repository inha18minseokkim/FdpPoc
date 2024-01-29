package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.interfaces.InnerProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InnerProductRepository extends JpaRepository<InnerProduct,Long> , InnerProductRepositoryCustom {
    List<InnerProduct> findAllByIsAvailable(boolean isAvailable);
}
