package com.example.fdppoc.service;

import com.example.fdppoc.entity.CustomerSearchHistory;
import com.example.fdppoc.repository.CustomerSearchHistoryRepository;
import com.example.fdppoc.service.dto.GetTopViewProductsCriteria;
import com.example.fdppoc.service.dto.GetTopViewProductsResult;
import com.example.fdppoc.service.dto.InsertProductHistoryCriteria;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSearchHistoryService {
    private final CustomerSearchHistoryRepository customerSearchHistoryRepository;
    @Transactional
    public void insertProductHistory(InsertProductHistoryCriteria in) {
        customerSearchHistoryRepository.save(CustomerSearchHistory
                .builder()
                        .innerProduct(in.getInnerProduct())
                        .memberInfo(in.getMemberInfo())
                        .regionGroup(in.getRegionGroup())
                        .submitTime(LocalDateTime.now())
                .build());
    }
    public GetTopViewProductsResult getTopViewProducts(GetTopViewProductsCriteria in){
        return null;
    }
}
