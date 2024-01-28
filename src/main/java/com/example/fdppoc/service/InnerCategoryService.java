package com.example.fdppoc.service;

import com.example.fdppoc.entity.InnerCategory;
import com.example.fdppoc.repository.InnerCategoryRepository;
import com.example.fdppoc.service.dto.*;
import com.example.fdppoc.service.mapper.InnerCategoryServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerCategoryService {
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerCategoryServiceMapper mapper;
    @Transactional
    public List<GetAllInnerProductsResult> getAllInnerProducts(GetAllInnerProductsCriteria in){
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
    public List<GetAllInnerCategoryResult> getAllInnerCategory(GetAllInnerCategoryCriteria in){
        List<InnerCategory> all = innerCategoryRepository.findAll();
        return all.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
    public void setInnerCategory(List<SetInnerCategoryCriteria> in){
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
