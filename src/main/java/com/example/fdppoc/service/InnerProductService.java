package com.example.fdppoc.service;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerCategory;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.InnerCategoryRepository;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.repository.InnerProductRepositoryCustom;
import com.example.fdppoc.repository.dto.FindInnerProductListIn;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.service.dto.*;
import com.example.fdppoc.service.mapper.InnerProductServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerProductService {
    private final InnerProductRepositoryCustom innerProductRepositoryCustom;
    private final InnerProductRepository innerProductRepository;
    private final BaseProductRepository baseProductRepository;
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerProductServiceMapper mapper;

    public List<GetInnerProductsWithFilterResult> getInnerProductsWithFilter(GetInnerProductsWithFilterCriteria in){
        List<FindInnerProductWithFilterOut> results = innerProductRepositoryCustom.findInnerProductWithFilter(mapper.from(in));
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Transactional
    public void setInnerProducts(List<SetInnerProductsCriteria> in){
        in.stream().filter((element) -> !element.getRowStatus().equals("R"))
                .map((element) -> {
                    InnerProduct entity = mapper.toEntity(element);
                    Optional<InnerCategory> resultInnerCategory = innerCategoryRepository.findById(element.getInnerCategoryId());

                    List<Long> baseProductIds = element.getBaseProductIds();
                    log.info("baseProductIds : {}",baseProductIds);
                    List<BaseProduct> baseProducts = baseProductIds.stream()
                            .map(baseProductId -> baseProductRepository.findById(baseProductId).orElseThrow())
                            .collect(Collectors.toList());
                    baseProducts.forEach(baseProductElement -> baseProductElement.setInnerProduct(entity));
                    entity.setInnerCategory(resultInnerCategory.orElseThrow());
                    entity.setBaseProducts(baseProducts);
                    return entity;
                }).forEach((element) -> innerProductRepository.save(element));

    }

    //내부상품리스트조회
    public List<GetInnerProductsResult> getInnerProductList(GetInnerProductsCriteria in){
        FindInnerProductListIn input = mapper.from(in);
        List<InnerProduct> innerProductList = innerProductRepositoryCustom.findInnerProductList(input);
        return innerProductList.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
}
