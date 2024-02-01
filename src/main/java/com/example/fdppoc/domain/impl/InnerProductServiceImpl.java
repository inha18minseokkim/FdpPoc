package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.domain.entity.InnerCategory;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.interfaces.InnerProductService;
import com.example.fdppoc.infrastructure.repository.BaseProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerCategoryRepository;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.impl.InnerProductRepositoryImpl;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListIn;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.domain.mapper.InnerProductServiceMapper;
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
public class InnerProductServiceImpl implements InnerProductService {
    private final InnerProductRepository innerProductRepository;
    private final BaseProductRepository baseProductRepository;
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerProductServiceMapper mapper;

    @Override
    public List<GetInnerProductsWithFilterResult> getInnerProductsWithFilter(GetInnerProductsWithFilterCriteria in){
        List<FindInnerProductWithFilterOut> results = innerProductRepository.findInnerProductWithFilter(mapper.from(in));
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void setInnerProducts(List<SetInnerProductsCriteria> in){
        in.stream().filter((element) -> !element.getRowStatus().equals("R"))
                .forEach((element) -> {
                    log.info("다음 저장 : {}",element);
                    InnerProduct entity = mapper.toEntity(element);
                    Optional<InnerCategory> resultInnerCategory = innerCategoryRepository.findById(element.getInnerCategoryId());

                    List<Long> baseProductIds = element.getBaseProductIds();
                    log.info("baseProductIds : {}",baseProductIds);
                    List<BaseProduct> baseProducts = baseProductIds.stream()
                            .map(baseProductId -> baseProductRepository.findById(baseProductId).orElseThrow())
                            .collect(Collectors.toList());
                    baseProducts.forEach(baseProductElement -> baseProductElement.setInnerProduct(entity));
                    entity.setInnerCategory(resultInnerCategory.orElseThrow());

                    innerProductRepository.save(entity);
                    baseProducts.forEach(baseProductElement -> {
                        baseProductElement.setInnerProduct(entity);
                        baseProductRepository.save(baseProductElement);
                    });

                });

    }

    //내부상품리스트조회
    @Override
    public List<GetInnerProductsResult> getInnerProductList(GetInnerProductsCriteria in){
        FindInnerProductListIn input = mapper.from(in);
        List<InnerProduct> innerProductList = innerProductRepository.findInnerProductList(input);
        return innerProductList.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
}
