package com.example.fdppoc.repository;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.QBaseProduct;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterIn;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterOut;
import com.example.fdppoc.repository.mapper.BaseProductRepositoryMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BaseProductRepositoryCustom {
    private final EntityManager em;
    private final BaseProductRepositoryMapper mapper;
    public List<FindBaseProductWithFilterOut> findBaseProductWithFilter(FindBaseProductWithFilterIn in) {
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
