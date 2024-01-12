package com.example.fdppoc.repository;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.*;
import com.example.fdppoc.repository.dto.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProcessedPriceInfoRepositoryCustom {
    private final EntityManager entityManager;

    public List<FindPriceListByGroupRegionCodeOut> findPriceListByGroupRegionCode(FindPriceListByGroupRegionCodeIn in){
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;
        QUserCode userCode1 = new QUserCode("a");
        QUserCode userCode2 = new QUserCode("b");
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        String startDate = LocalDate.parse(in.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
                .minusDays(in.getRangeForLength().getGapDay()-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<Tuple> results = query.select(
                processedPriceInfo.baseDate,
                processedPriceInfo.price.avg()

                )
                .from(processedPriceInfo, userGroupCode, userCode2)
                .innerJoin(processedPriceInfo.baseProduct, baseProduct)
                .innerJoin(processedPriceInfo.regionInfo, userCode1)
                .where(
                        processedPriceInfo.baseRange.eq(in.getRangeForTag())
                                .and(processedPriceInfo.baseDate.between(startDate, in.getBaseDate()))
                                .and(userGroupCode.id.eq(in.getRegionGroup().getId()))
                                .and(userCode1.id.eq(userCode2.codeDetailName.castToNum(Long.class)))
                                .and(userGroupCode.id.eq(userCode2.userGroupCode.id))
                ).groupBy(
                        processedPriceInfo.baseDate
                )
                .fetch();
        return results.stream().map((element) -> FindPriceListByGroupRegionCodeOut.builder()
                .baseDate(in.getBaseDate())
                .price(element.get(processedPriceInfo.price.avg()).longValue())
                .regionGroupInfo(in.getRegionGroup())
                .baseProduct(in.getTargetProduct())
                .baseRange(in.getRangeForLength())
                .build()
        ).collect(Collectors.toList());
    }

}
