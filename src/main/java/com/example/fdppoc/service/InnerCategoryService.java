package com.example.fdppoc.service;

import com.example.fdppoc.entity.InnerCategory;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.InnerCategoryRepository;
import com.example.fdppoc.service.dto.GetAllInnerProductsIn;
import com.example.fdppoc.service.dto.GetAllInnerProductsOut;
import com.example.fdppoc.service.dto.GetAllInnerProductsOutElement;
import com.example.fdppoc.service.mapper.InnerCategoryServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerCategoryService {
    private final InnerCategoryRepository innerCategoryRepository;
    private final InnerCategoryServiceMapper mapper;
    @Transactional
    public List<GetAllInnerProductsOut> getAllInnerProducts(GetAllInnerProductsIn in){
        List<InnerCategory> all = innerCategoryRepository.findAll();
        List<GetAllInnerProductsOut> result = all.stream().filter(categoryElement -> categoryElement.getIsAvailable() == true)
                .map(categoryElement -> {
                    List<GetAllInnerProductsOutElement> subList = categoryElement.getSubProducts().stream()
                            .filter(element -> element.getIsAvailable() == true)
                            .map(element -> mapper.from(element))
                            .collect(Collectors.toList());

                    return GetAllInnerProductsOut.builder()
                            .innerProducts(subList)
                            .id(categoryElement.getId())
                            .innerCategoryName(categoryElement.getInnerCategoryName())
                            .orderSequence(categoryElement.getOrderSequence())
                            .additionalDescription(categoryElement.getAdditionalDescription()).build();

                }).collect(Collectors.toList());
        return result;
    }
}
