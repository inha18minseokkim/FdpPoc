package com.example.fdppoc.controller;

import com.example.fdppoc.code.ControllerResponse;
import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.MainProductListControllerMapper;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.interfaces.InnerCategoryService;
import com.example.fdppoc.domain.interfaces.InnerProductService;
import com.example.fdppoc.domain.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mainProductList")
@Slf4j
public class MainProductListController {
    private final InnerProductService innerProductService;
    private final ProductService productService;
    private final InnerCategoryService innerCategoryService;
    private final MainProductListControllerMapper mapper;
    @GetMapping("/searchInnerProducts")
    public SearchInnerProductsResponse searchInnerProducts(SearchInnerProductsRequest request){
        log.info("받음 : {}", request);
        GetInnerProductsCriteria from = GetInnerProductsCriteria.builder().isAvailable(true).build();
        List<GetInnerProductsResult> results = innerProductService.getInnerProductList(from);
        return SearchInnerProductsResponse.builder()
                //.lists(results.stream().collect(Collectors.toList()))
                .responseCode(ControllerResponse.OK).build();
    }
    @GetMapping("/searchAllInnerProducts") //CKBFP01000008 전체상품검색
    public SearchAllInnerProductsResponse searchAllInnerProducts(SearchAllInnerProductsRequest request){
        List<GetAllInnerProductsResult> allInnerProducts = innerCategoryService.getAllInnerProducts(GetAllInnerProductsCriteria.builder().build());
        return SearchAllInnerProductsResponse.builder()
                .baseDate(request.getBaseDate())
                .innerCategories(allInnerProducts.stream().map(element -> mapper.from2(element)).collect(Collectors.toList()))
                .build();
    }

    @GetMapping("/legacyAllInnerProducts") //CKBFP01000010 주제단위상품정보조회
    public LegacyAllInnerProductsResponse legacyAllInnerProducts(LegacyAllInnerProductsRequest request){
        GetLatestBaseDateResult latestBaseDate = productService.getLatestBaseDate(GetLatestBaseDate.builder().baseDate(request.getBaseDate()).build());
        GetAllProductCriteria input = mapper.from(request);
        input.setBaseDate(latestBaseDate.getBaseDate());
        List<GetAllProductResult> allProduct = productService.getAllProduct(input);
        return LegacyAllInnerProductsResponse.builder()
                .list(allProduct.stream().map(element -> mapper.from(element)).collect(Collectors.toList()))
                .baseDate(latestBaseDate.getBaseDate())
                .regionGroupId(request.getRegionGroupId())
                .customerId(request.getCustomerId())
                .compareDsCode("01")
                .processCount(Long.valueOf(allProduct.size()))
                .build();
    }
}
