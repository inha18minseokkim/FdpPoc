package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerCategory;
import com.example.fdppoc.domain.interfaces.InnerCategoryService;
import com.example.fdppoc.infrastructure.repository.InnerCategoryRepository;
import com.example.fdppoc.domain.mapper.InnerCategoryServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerCategoryServiceImpl implements InnerCategoryService {
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerCategoryServiceMapper mapper;
    @Override
    @Transactional
    @Cacheable(value = "InnerCategoryServiceImpl.getAllInnerProducts", key="#in")
    public List<GetAllInnerProductsResult> getAllInnerProducts(GetAllInnerProductsCriteria in){
        //카테고리별로 모든 내부상품코드 조회
        List<InnerCategory> all = innerCategoryRepository.findAll();
        List<GetAllInnerProductsResult> result = all.stream().filter(categoryElement -> categoryElement.getIsAvailable() == true)
                .map(categoryElement -> {
                    List<GetAllInnerProductsResultElement> subList = categoryElement.getSubProducts().stream()
                            .filter(element -> element.getIsAvailable() == true)
                            .map(element -> mapper.from(element))
                            .collect(Collectors.toList());

                    return GetAllInnerProductsResult.builder()
                            .innerProducts(subList)
                            .id(categoryElement.getId())
                            .innerCategoryName(categoryElement.getInnerCategoryName())
                            .orderSequence(categoryElement.getOrderSequence())
                            .additionalDescription(categoryElement.getAdditionalDescription()).build();

                }).collect(Collectors.toList());
        return result;
    }
    @Override
    public List<GetAllInnerCategoryResult> getAllInnerCategory(GetAllInnerCategoryCriteria in){
        //웹스퀘어 관리자용 모든 카테고리 조회
        List<InnerCategory> all = innerCategoryRepository.findAll();
        return all.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
    @Override
    public void setInnerCategory(List<SetInnerCategoryCriteria> in){
        //웹스퀘어 관리자용 카테고리 수정
        in.stream().filter(element -> !element.getRowStatus().equals("R")).forEach(
                element -> {
                    if(element.getId() == null || innerCategoryRepository.findById(element.getId()).isEmpty())
                        innerCategoryRepository.save(mapper.from(element));
                    else {
                        InnerCategory targetInnerCategory = innerCategoryRepository.findById(element.getId()).get();
                        targetInnerCategory.setInnerCategoryName(element.getInnerCategoryName());
                        targetInnerCategory.setIsAvailable(element.getIsAvailable());
                        targetInnerCategory.setAdditionalDescription(element.getAdditionalDescription());
                        targetInnerCategory.setOrderSequence(element.getOrderSequence());
                        innerCategoryRepository.save(targetInnerCategory);
                    }
                }
        );
    }
}
