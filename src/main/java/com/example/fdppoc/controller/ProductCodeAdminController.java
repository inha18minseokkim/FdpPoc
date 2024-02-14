package com.example.fdppoc.controller;

import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.ProductCodeAdminControllerMapper;
import com.example.fdppoc.domain.dto.GetAllInnerCategoryResult;
import com.example.fdppoc.domain.dto.GetBaseCodesResult;
import com.example.fdppoc.domain.dto.GetInnerProductsWithFilterResult;
import com.example.fdppoc.domain.interfaces.BaseProductService;
import com.example.fdppoc.domain.interfaces.InnerCategoryService;
import com.example.fdppoc.domain.interfaces.InnerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productCodeAdmin")
@RequiredArgsConstructor
public class ProductCodeAdminController {
    private final BaseProductService baseProductService;
    private final InnerProductService innerProductService;
    private final InnerCategoryService innerCategoryService;
    private final ProductCodeAdminControllerMapper mapper;
    @GetMapping("/v1/getBaseCodes")
    public ResponseEntity<GetBaseCodesResponse> getBaseCodes(GetBaseCodesRequest request){
        List<GetBaseCodesResult> baseCodes = baseProductService.getBaseCodes(mapper.from(request));
        return ResponseEntity.ok().body(
                GetBaseCodesResponse.builder()
                .responseCode("001")
                .list(baseCodes.stream().map((element) -> mapper.from(element)).collect(Collectors.toList()))
                .build());
    }
    @PostMapping("/v1/setBaseCodes")
    public ResponseEntity<SetBaseCodesResponse> setBaseCodes(@RequestBody SetBaseCodesRequest request){
        baseProductService.setBaseCodes(request.getLists().stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(
                SetBaseCodesResponse.builder().responseCode(ControllerResponse.OK).build());
    }
    @GetMapping("/v1/getInnerProducts")
    public ResponseEntity<GetInnerProductsResponse> getInnerProducts(GetInnerProductsRequest request){
        List<GetInnerProductsWithFilterResult> results = innerProductService.getInnerProductsWithFilter(mapper.from(request));
        return ResponseEntity.ok().body(
                GetInnerProductsResponse.builder()
                .responseCode("001")
                .lists(results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList()))
                .build());
    }
    @PostMapping("/v1/setInnerProducts")
    public ResponseEntity<SetInnerProductsResponse> setInnerProducts(@RequestBody SetInnerProductsRequest request) {
        innerProductService.setInnerProducts(request.getLists().stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(
                SetInnerProductsResponse.builder().responseCode(ControllerResponse.OK).build());
    }
    @GetMapping("/v1/getInnerCategory")
    public ResponseEntity<GetInnerCategoryResponse> getInnerCategory(GetInnerCategoryRequest request){
        List<GetAllInnerCategoryResult> allInnerCategory = innerCategoryService.getAllInnerCategory(mapper.from(request));
        return ResponseEntity.ok().body(
                GetInnerCategoryResponse.builder()
                        .list(allInnerCategory.stream()
                        .map(element -> mapper.from(element)).collect(Collectors.toList()))
                        .build());
    }

}
