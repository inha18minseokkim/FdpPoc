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
import java.util.Map;
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
    public GetPriceDiffOut getTodayAndWeeklyMeanPrice(GetPriceDiffIn in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;
        QUserCode userCode = QUserCode.userCode;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<Tuple> result = query.select(
                        processedPriceInfo.price.avg()
                        ,processedPriceInfo.baseDate
                )
                .from(processedPriceInfo, userGroupCode, innerProduct, userCode)
                .where(
                        processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                .and(processedPriceInfo.baseRange.eq(BaseRange.DAY))
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName.castToNum(Long.class)))
                                .and(userGroupCode.eq(in.getRegionGroup()))
                                .and(userCode.userGroupCode.eq(in.getRegionGroup()))
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                .and(innerProduct.eq(in.getTargetProduct()))
                ).groupBy(
                        processedPriceInfo.baseDate
                ).orderBy(
                        processedPriceInfo.baseDate.asc()
                )
                .fetch();
        return GetPriceDiffOut.builder()
                .basePrice(result.getLast().get(processedPriceInfo.price.avg()).longValue())
                .baseDate(result.getLast().get(processedPriceInfo.baseDate))
                .meanPrice(result.stream()
                        .map(element->element.get(processedPriceInfo.price.avg()))
                        .reduce((a, b) -> a+b).get().longValue()/result.size()
                )
                .pastDate(result.get(0).get(processedPriceInfo.baseDate))
                .build();
    }
    //Legacy용
    public Map<Long,InnerProduct> getAllProduct(GetAllProductIn in){
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        List<InnerProduct> result = query.select(innerProduct)
                .from(innerProduct)
                .where(innerProduct.isAvailable.eq(true)).fetch();
        return result.stream().collect(Collectors.toMap(InnerProduct::getId,element -> element));
    }

    public List<GetPriceDiffListOut> getPriceDiffList(GetPriceDiffListIn in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;
        QUserCode userCode = QUserCode.userCode;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);

        List<Tuple> result = query.select(
                        innerProduct
                        ,processedPriceInfo.baseDate
                        ,processedPriceInfo.price.avg()
                )
                .from(processedPriceInfo, userGroupCode, innerProduct, userCode)
                .where(
                        processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                .and(processedPriceInfo.baseRange.eq(BaseRange.DAY))
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName.castToNum(Long.class)))
                                .and(userGroupCode.eq(in.getRegionGroup()))
                                .and(userCode.userGroupCode.eq(in.getRegionGroup()))
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                ).groupBy(
                        innerProduct,
                        processedPriceInfo.baseDate
                )
                .fetch();
        //log.info("실행결과 : {}",result);
        return result.stream().map(element -> GetPriceDiffListOut.builder().innerProduct(element.get(innerProduct))
                .price(element.get(processedPriceInfo.price.avg()))
                .baseDate(element.get(processedPriceInfo.baseDate))
                .build()).collect(Collectors.toList());
    }
}