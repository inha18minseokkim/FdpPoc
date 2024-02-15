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
/*
*
*       관리자화면 컨트롤러는 관리자화면 만들고나서 다시 뜯어고쳐야함(웹스퀘어 스펙으로 안갈예정)
* */
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
                .list(baseCodes.stream().map((element) -> mapper.from(element)).collect(Collectors.toList()))
                .build());
    }
    @PostMapping("/v1/setBaseCodes")
    public ResponseEntity<SetBaseCodesResponse> setBaseCodes(@RequestBody SetBaseCodesRequest request){
        baseProductService.setBaseCodes(request.getLists().stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(
                SetBaseCodesResponse.builder().processCount("1").build());
    }
    @GetMapping("/v1/getInnerProducts")
    public ResponseEntity<GetInnerProductsResponse> getInnerProducts(GetInnerProductsRequest request){
        List<GetInnerProductsWithFilterResult> results = innerProductService.getInnerProductsWithFilter(mapper.from(request));
        return ResponseEntity.ok().body(
                GetInnerProductsResponse.builder()
                .lists(results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList()))
                .build());
    }
    @PostMapping("/v1/setInnerProducts")
    public ResponseEntity<SetInnerProductsResponse> setInnerProducts(@RequestBody SetInnerProductsRequest request) {
        innerProductService.setInnerProducts(request.getLists().stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(
                SetInnerProductsResponse.builder().processCount("1").build());
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
