package com.example.fdppoc.service;

import com.example.fdppoc.entity.CustomerSearchHistory;
import com.example.fdppoc.repository.CustomerSearchHistoryRepository;
import com.example.fdppoc.service.dto.GetTopViewProductsIn;
import com.example.fdppoc.service.dto.GetTopViewProductsOut;
import com.example.fdppoc.service.dto.InsertProductHistoryIn;
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
    public void insertProductHistory(InsertProductHistoryIn in) {
        customerSearchHistoryRepository.save(CustomerSearchHistory
                .builder()
                        .baseProduct(in.getBaseProduct())
                        .memberInfo(in.getMemberInfo())
                        .regionGroup(in.getRegionGroup())
                        .submitTime(LocalDateTime.now())
                .build());
    }
    public GetTopViewProductsOut getTopViewProducts(GetTopViewProductsIn in){
        return null;
    }
}
