package com.example.fdppoc.service;

import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.BaseProductRepositoryCustom;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterOut;
import com.example.fdppoc.service.dto.GetBaseCodesCriteria;
import com.example.fdppoc.service.dto.GetBaseCodesResult;
import com.example.fdppoc.service.dto.SetBaseCodesCriteria;
import com.example.fdppoc.service.mapper.BaseProductServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BaseProductService {
    private final BaseProductRepositoryCustom baseProductRepositoryCustom;
    private final BaseProductRepository baseProductRepository;
    private final BaseProductServiceMapper mapper;
    public List<GetBaseCodesResult> getBaseCodes(GetBaseCodesCriteria in){
        List<FindBaseProductWithFilterOut> results = baseProductRepositoryCustom
                .findBaseProductWithFilter(mapper.from(in));
        return results.stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Transactional
    public void setBaseCodes(List<SetBaseCodesCriteria> in){
        in.stream().filter((element) -> !element.getRowStatus().equals("R"))
                .map((element) -> mapper.toEntity(element))
                .forEach((element) -> {
                    log.debug("save 시도 : {}",element);
                    baseProductRepository.save(element);
                });
    }
}
