package com.example.fdppoc.service;

import com.example.fdppoc.repository.ProcessedPriceInfoRepositoryCustom;
import com.example.fdppoc.repository.dto.FindPriceListByGroupRegionCodeOut;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import com.example.fdppoc.service.mapper.ProductDetailServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    private final ProductDetailServiceMapper mapper;
    public GetProductPriceOut getProductPriceOut(GetProductPriceIn in){
        List<FindPriceListByGroupRegionCodeOut> results = processedPriceInfoRepositoryCustom.findPriceListByGroupRegionCode(mapper.from(in));

        return GetProductPriceOut.builder()
           //     .meanPrice() 구현중 집가서
                .priceList(results.stream().map(element->element.getPrice()).collect(Collectors.toList()))
                .baseRange(in.getFindRange())
                .build();
    }
}
