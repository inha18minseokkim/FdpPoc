package com.example.fdppoc.repository;

import com.example.fdppoc.entity.ProcessedPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedPriceInfoRepository extends JpaRepository<ProcessedPriceInfo,Long> {

}
