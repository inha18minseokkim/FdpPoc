package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.interfaces.BaseProductService;
import com.example.fdppoc.infrastructure.repository.BaseProductRepository;
import com.example.fdppoc.infrastructure.impl.BaseProductRepositoryImpl;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOut;
import com.example.fdppoc.domain.dto.GetBaseCodesCriteria;
import com.example.fdppoc.domain.dto.GetBaseCodesResult;
import com.example.fdppoc.domain.dto.SetBaseCodesCriteria;
import com.example.fdppoc.domain.mapper.BaseProductDomainMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BaseProductServiceImpl implements BaseProductService {
    private final BaseProductRepository baseProductRepository;
    private final BaseProductDomainMapper mapper;
    @Override
    public List<GetBaseCodesResult> getBaseCodes(GetBaseCodesCriteria in){
        List<FindBaseProductWithFilterOut> results = baseProductRepository
                .findBaseProductWithFilter(mapper.from(in));
        return results.stream()
                .map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Override
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
