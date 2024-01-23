package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.QBaseProduct;
import com.example.fdppoc.entity.QInnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductListIn;
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
        if(in.getInnerCategoryId() != null)
            booleanBuilder.and(innerProduct.innerCategory.id.eq(in.getInnerCategoryId()));
        List<InnerProduct> results = query.select(innerProduct)
                .from(innerProduct).innerJoin(innerProduct.baseProducts,baseProduct)
                .where(booleanBuilder)
                .fetch();
        return results.stream().map((element) -> mapper.from(element)).collect(Collectors.toList());
    }
    public List<InnerProduct> findInnerProductList(FindInnerProductListIn in){
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
        return results;
    }
    //좋아요 누른 상품들
    //가격 상하
}
