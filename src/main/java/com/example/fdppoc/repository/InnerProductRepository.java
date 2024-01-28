package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InnerProductRepository extends JpaRepository<InnerProduct,Long> {
    List<InnerProduct> findAllByIsAvailable(boolean isAvailable);
}
