package com.example.fdppoc.service;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.repository.InnerProductRepositoryCustom;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterOut;
import com.example.fdppoc.service.dto.SetInnerProductsIn;
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
    private final InnerProductServiceMapper mapper;

    public List<GetInnerProductsWithFilterOut> getInnerProductsWithFilter(GetInnerProductsWithFilterIn in){
        List<FindInnerProductWithFilterOut> results = innerProductRepositoryCustom.findInnerProductWithFilter(mapper.from(in));
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Transactional
    public void setInnerProducts(List<SetInnerProductsIn> in){
        in.stream().filter((element) -> !element.getRowStatus().equals("R"))
                .map((element) -> {
                    InnerProduct entity = mapper.toEntity(element);
//                    Optional<BaseProduct> result = baseProductRepository.findByCategoryCodeAndItemCodeAndKindCodeAndClassCodeAndGradeCode
//                            (element.getCategoryCode(), element.getItemCode(), element.getKindCode(), element.getClassCode(), element.getGradeCode());
                    Optional<BaseProduct> result = baseProductRepository.findById(element.getBaseProductId());
                    entity.setBaseProduct(result.orElseThrow());
                    return entity;
                }).forEach((element) -> innerProductRepository.save(element));

    }
}
