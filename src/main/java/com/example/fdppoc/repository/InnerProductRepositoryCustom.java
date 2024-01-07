package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.QBaseProduct;
import com.example.fdppoc.entity.QInnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.repository.dto.FindInnerProductsWithFilterIn;
import com.example.fdppoc.repository.mapper.InnerProductRepositoryMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InnerProductRepositoryCustom {
    private final EntityManager em;
    private final InnerProductRepositoryMapper mapper;

    public List<FindInnerProductWithFilterOut> findInnerProductWithFilter(FindInnerProductsWithFilterIn in){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(in.getCategoryCode() != null)
            booleanBuilder.and(baseProduct.categoryCode.eq(in.getCategoryCode()));

        List<InnerProduct> results = query.select(innerProduct)
                .from(innerProduct).join(baseProduct)
                .where(booleanBuilder)
                .fetch();
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
}
