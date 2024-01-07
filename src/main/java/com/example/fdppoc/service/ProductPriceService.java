package com.example.fdppoc.service;

import com.example.fdppoc.entity.QInnerProduct;
import com.example.fdppoc.entity.QOriginalPriceInfo;
import com.example.fdppoc.entity.QUserCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceService {
    private final EntityManager em;

    public void graph() {
//        JPAQueryFactory query = new JPAQueryFactory(em);
//        QOriginalPriceInfo originalPriceInfo = QOriginalPriceInfo.originalPriceInfo;
//        QUserCode userCode = QUserCode.userCode;
//        query.
//                selectFrom(originalPriceInfo)
//                .join(userCode)
//                .where(userCode.)


    }

}
