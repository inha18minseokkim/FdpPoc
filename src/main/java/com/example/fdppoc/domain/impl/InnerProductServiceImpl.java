package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.domain.entity.InnerCategory;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.interfaces.InnerProductService;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.interfaces.InnerProductReader;
import com.example.fdppoc.infrastructure.repository.BaseProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerCategoryRepository;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListInDto;
import com.example.fdppoc.domain.mapper.InnerProductServiceMapper;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerProductServiceImpl implements InnerProductService {
    private final InnerProductReader innerProductReader;
    private final InnerProductRepository innerProductRepository;
    private final BaseProductRepository baseProductRepository;
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerProductServiceMapper mapper;

    @Override
    public List<GetInnerProductsWithFilterResult> getInnerProductsWithFilter(GetInnerProductsWithFilterCriteria in){
        //웹스퀘어 관리자화면용 필터 내부상품목록 조회
        List<FindInnerProductWithFilterOutDto> results = innerProductReader.findInnerProductWithFilter(mapper.from(in));
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void setInnerProducts(List<SetInnerProductsCriteria> in){
        //웹스퀘어 관리자화면용 필터 내부상품목록 수정
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
    @Cacheable(value="InnerProductServiceImpl.getInnerProductList",key = "#in")
    public List<GetInnerProductsResult> getInnerProductList(GetInnerProductsCriteria in){
        //현재 노출 가능한 내부상품 리스트 리턴
        FindInnerProductListInDto input = mapper.from(in);
        List<FindInnerProductListOutDto> innerProductList = innerProductReader.findInnerProductList(input);
        List<InnerProduct> targetInnerProducts = innerProductList.stream().map(element -> innerProductRepository.findById(element.getInnerProductId()).orElseThrow())
                .collect(Collectors.toList());
        return targetInnerProducts.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
}
