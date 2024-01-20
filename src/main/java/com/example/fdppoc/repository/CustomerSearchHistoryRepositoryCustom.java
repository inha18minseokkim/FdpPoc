package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.QBaseProduct;
import com.example.fdppoc.entity.QCustomerSearchHistory;
import com.example.fdppoc.entity.QInnerProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomerSearchHistoryRepositoryCustom {
    private final EntityManager entityManager;

    List<InnerProduct> getTopViewedInnerProduct(LocalDateTime currentTime,Integer rangeHour){
        LocalDateTime startTime = currentTime.minusHours(rangeHour);

//        QInnerProduct innerProduct = QInnerProduct.innerProduct;
//        QCustomerSearchHistory customerSearchHistory = QCustomerSearchHistory.customerSearchHistory;
//        QBaseProduct baseProduct = QBaseProduct.baseProduct;
//        JPAQueryFactory query = new JPAQueryFactory(entityManager);
//        query.select(innerProduct,innerProduct.count())
//                .from(customerSearchHistory)
//                .join(customerSearchHistory.baseProduct,innerProduct.baseProduct)
//                .where(
//                        customerSearchHistory.submitTime.between(startTime,currentTime)
//                                .and(innerProduct.baseProduct.isAvailable.eq(true))
//                )
//                .groupBy(innerProduct)
//                .fetch();
        return null;
    }
}
