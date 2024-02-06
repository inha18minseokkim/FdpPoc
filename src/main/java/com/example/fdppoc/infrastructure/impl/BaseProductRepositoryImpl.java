package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.domain.entity.*;
import com.example.fdppoc.infrastructure.interfaces.BaseProductRepositoryCustom;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterInDto;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.mapper.BaseProductRepositoryMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BaseProductRepositoryImpl implements BaseProductRepositoryCustom {
    private final EntityManager em;
    private final BaseProductRepositoryMapper mapper;
    @Override
    public List<FindBaseProductWithFilterOutDto> findBaseProductWithFilter(FindBaseProductWithFilterInDto in) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QBaseProduct baseProduct = QBaseProduct.baseProduct;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(in.getCategoryCode() != null) booleanBuilder.and(baseProduct.categoryCode.eq(in.getCategoryCode()));
        if(in.getItemCode() != null) booleanBuilder.and(baseProduct.itemCode.eq(in.getItemCode()));

        List<BaseProduct> results = query.selectFrom(baseProduct)
                .where(
                        booleanBuilder
                ).fetch();
        return results.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
}
