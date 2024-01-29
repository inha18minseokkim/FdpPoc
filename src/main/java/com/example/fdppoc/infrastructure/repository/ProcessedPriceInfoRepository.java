package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.ProcessedPriceInfo;
import com.example.fdppoc.infrastructure.interfaces.ProcessedPriceInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedPriceInfoRepository extends JpaRepository<ProcessedPriceInfo,Long> , ProcessedPriceInfoRepositoryCustom {

}
