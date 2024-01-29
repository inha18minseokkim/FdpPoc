package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductIn;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductOut;
import com.example.fdppoc.infrastructure.interfaces.CustomerSearchHistoryRepositoryCustom;
import com.querydsl.core.Tuple;
import com.example.fdppoc.domain.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomerSearchHistoryRepositoryImpl implements CustomerSearchHistoryRepositoryCustom {
    private final EntityManager entityManager;

    public List<GetTopViewedInnerProductOut> getTopViewedInnerProduct(GetTopViewedInnerProductIn in){
        LocalDateTime startTime = in.getCurrentTime().minusHours(in.getRangeHour());

        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        QCustomerSearchHistory customerSearchHistory = QCustomerSearchHistory.customerSearchHistory;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<Tuple> results = query
                .select(innerProduct, innerProduct.count())
                .from(customerSearchHistory)
                .join(customerSearchHistory.innerProduct, innerProduct)
                .where(
                        customerSearchHistory.submitTime.between(startTime, in.getCurrentTime())
                                .and(innerProduct.isAvailable.eq(true))
                )
                .groupBy(innerProduct)
                .orderBy(innerProduct.count().desc())
                .fetch();
        return results.stream().map(element -> GetTopViewedInnerProductOut
                .builder()
                .count(element.get(innerProduct.count()))
                .innerProduct(element.get(innerProduct)).build()).collect(Collectors.toList());
    }
}