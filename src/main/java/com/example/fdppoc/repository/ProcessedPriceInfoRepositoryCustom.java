package com.example.fdppoc.repository;

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
        QUserCode userCode = QUserCode.userCode;
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        String startDate = LocalDate.parse(in.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
                .minusDays(in.getRangeForLength().getGapDay()-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<Tuple> results = query.select(
                processedPriceInfo.baseDate,
                processedPriceInfo.price.avg()
                )
                .from(processedPriceInfo, userGroupCode,innerProduct,userCode)
                .where(
                        processedPriceInfo.baseRange.eq(in.getRangeForTag())
                                .and(processedPriceInfo.baseDate.between(startDate, in.getBaseDate()))
                                .and(userGroupCode.id.eq(in.getRegionGroup().getId()))
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName.castToNum(Long.class)))
                                .and(userGroupCode.id.eq(userCode.userGroupCode.id)
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                                .and(innerProduct.eq(in.getTargetProduct()))
                                )
                ).groupBy(
                        processedPriceInfo.baseDate
                )
                .fetch();
        return results.stream().map((element) -> FindPriceListByGroupRegionCodeOut.builder()
                .baseDate(in.getBaseDate())
                .price(element.get(processedPriceInfo.price.avg()).longValue())
                .regionGroupInfo(in.getRegionGroup())
                .innerProduct(in.getTargetProduct())
                .baseRange(in.getRangeForLength())
                .build()
        ).collect(Collectors.toList());
    }
    public GetMinMaxPriceOut getMinMaxPrice(GetMinMaxPriceIn in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;
        QUserCode userCode = QUserCode.userCode;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<Tuple> result = query.select(
                        processedPriceInfo.price.max()
                        , processedPriceInfo.price.min()
                )
                .from(processedPriceInfo, userGroupCode, innerProduct, userCode)
                .where(
                        processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName.castToNum(Long.class)))
                                .and(userGroupCode.eq(in.getRegionGroup()))
                                .and(userCode.userGroupCode.eq(in.getRegionGroup()))
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                .and(innerProduct.eq(in.getTargetProduct()))
                ).groupBy(
                        processedPriceInfo.baseDate
                ).fetch();
        return GetMinMaxPriceOut.builder()
                .maxPrice(result.get(0).get(processedPriceInfo.price.max()).longValue())
                .minPrice(result.get(0).get(processedPriceInfo.price.min()).longValue())
                .build();
    }

}