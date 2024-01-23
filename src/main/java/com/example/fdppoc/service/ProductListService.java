package com.example.fdppoc.service;

import com.example.fdppoc.repository.CustomerSearchHistoryRepositoryCustom;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.repository.InnerProductRepositoryCustom;
import com.example.fdppoc.repository.ProcessedPriceInfoRepositoryCustom;
import com.example.fdppoc.repository.dto.GetTopViewedInnerProductOut;
import com.example.fdppoc.service.dto.GetPopularProductCriteria;
import com.example.fdppoc.service.dto.GetPopularProductResult;
import com.example.fdppoc.service.mapper.ProductListServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListService {
    private final CustomerSearchHistoryRepositoryCustom customerSearchHistoryRepositoryCustom;
    private final ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    private final ProductListServiceMapper mapper;
    //인기상품리스트조회
    public List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria){
        List<GetTopViewedInnerProductOut> results = customerSearchHistoryRepositoryCustom.getTopViewedInnerProduct(mapper.from(criteria));

        return null;
    }
    //관심상품리스트조회
    //자주먹는상품리스트조회
    //가격변동TOP5 상품리스트조회
}
