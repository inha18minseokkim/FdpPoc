package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.*;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductsWithFilterInDto;
import com.example.fdppoc.infrastructure.interfaces.InnerProductReader;
import com.example.fdppoc.infrastructure.mapper.InnerProductRepositoryMapper;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListInDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InnerProductReaderImpl implements InnerProductReader {
    private final EntityManager em;
    private final InnerProductRepositoryMapper mapper;
    @Override
    public List<FindInnerProductWithFilterOutDto> findInnerProductWithFilter(FindInnerProductsWithFilterInDto in){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(in.getCategoryCode() != null)
            booleanBuilder.and(baseProduct.categoryCode.eq(in.getCategoryCode()));
        if(in.getInnerCategoryId() != null)
            booleanBuilder.and(innerProduct.innerCategory.id.eq(in.getInnerCategoryId()));
        List<InnerProduct> results = query.select(innerProduct)
                .from(innerProduct).innerJoin(innerProduct.baseProducts,baseProduct)
                .where(booleanBuilder)
                .fetch();
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "InnerProductReaderImpl.findInnerProductList", key="#in")
    public List<FindInnerProductListOutDto> findInnerProductList(FindInnerProductListInDto in){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(in.getInnerCategoryId() != null)
            booleanBuilder.and(innerProduct.innerCategory.id.eq(in.getInnerCategoryId()));
        if(in.getSearchKeyword() != null)
            booleanBuilder.and(innerProduct.productName.like("%"+in.getSearchKeyword()+"%"));
        List<InnerProduct> results = query.select(innerProduct)
                .from(innerProduct)
                .where(
                        innerProduct.isAvailable.eq(in.getIsAvailable())
                                .and(booleanBuilder)
                )
                .fetch();
        return results.stream().map(element -> mapper.from2(element)).toList();
    }

    //좋아요 누른 상품들
    //가격 상하
}
