package com.example.fdppoc.controller;

import com.example.fdppoc.code.ControllerResponse;
import com.example.fdppoc.controller.dto.SearchAllInnerProductsRequest;
import com.example.fdppoc.controller.dto.SearchAllInnerProductsResponse;
import com.example.fdppoc.controller.dto.SearchInnerProductsRequest;
import com.example.fdppoc.controller.dto.SearchInnerProductsResponse;
import com.example.fdppoc.controller.mapper.MainProductListControllerMapper;
import com.example.fdppoc.service.InnerCategoryService;
import com.example.fdppoc.service.InnerProductService;
import com.example.fdppoc.service.dto.GetAllInnerProductsIn;
import com.example.fdppoc.service.dto.GetAllInnerProductsOut;
import com.example.fdppoc.service.dto.GetInnerProductListIn;
import com.example.fdppoc.service.dto.GetInnerProductListOut;
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
    private final InnerCategoryService innerCategoryService;
    private final MainProductListControllerMapper mapper;
    @GetMapping("/searchInnerProducts")
    public SearchInnerProductsResponse searchInnerProducts(SearchInnerProductsRequest request){
        log.info("받음 : {}", request);
        GetInnerProductListIn from = GetInnerProductListIn.builder().isAvailable(true).build();
        List<GetInnerProductListOut> results = innerProductService.getInnerProductList(from);
        return SearchInnerProductsResponse.builder()
                //.lists(results.stream().collect(Collectors.toList()))
                .responseCode(ControllerResponse.OK).build();
    }
    @GetMapping("/searchAllInnerProducts")
    public SearchAllInnerProductsResponse searchAllInnerProducts(SearchAllInnerProductsRequest request){
        List<GetAllInnerProductsOut> allInnerProducts = innerCategoryService.getAllInnerProducts(GetAllInnerProductsIn.builder().build());
        return SearchAllInnerProductsResponse.builder()
                .baseDate(request.getBaseDate())
                .innerCategories(allInnerProducts.stream().map(element -> mapper.from2(element)).collect(Collectors.toList()))
                .build();

    }
}
