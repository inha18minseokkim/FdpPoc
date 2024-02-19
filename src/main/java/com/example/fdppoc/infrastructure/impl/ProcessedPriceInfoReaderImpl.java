package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.entity.*;
import com.example.fdppoc.infrastructure.dto.*;
import com.example.fdppoc.infrastructure.interfaces.ProcessedPriceInfoReader;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProcessedPriceInfoReaderImpl implements ProcessedPriceInfoReader {
    private final EntityManager entityManager;
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final InnerProductRepository innerProductRepository;
    @Override
    public List<FindPriceListByGroupRegionCodeOut> findPriceListByGroupRegionCode(FindPriceListByGroupRegionCodeInDto in){
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
                processedPriceInfo.price.avg(),
                userGroupCode,
                innerProduct
                )
                .from(processedPriceInfo, userGroupCode,innerProduct,userCode)
                .where(
                        processedPriceInfo.baseRange.eq(in.getRangeForTag())
                                .and(processedPriceInfo.baseDate.between(startDate, in.getBaseDate()))
                                .and(userGroupCode.id.eq(in.getRegionGroupId()))
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName))
                                .and(userGroupCode.id.eq(userCode.userGroupCode.id)
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                                .and(innerProduct.id.eq(in.getTargetInnerProductId()))
                                )
                ).groupBy(
                        processedPriceInfo.baseDate,
                        userGroupCode,
                        innerProduct
                )
                .fetch();
        return results.stream().map((element) -> FindPriceListByGroupRegionCodeOut.builder()
                .baseDate(in.getBaseDate())
                .price(element.get(processedPriceInfo.price.avg()).longValue())
                .regionGroupId(element.get(userGroupCode).getId())
                .innerProductId(element.get(innerProduct).getId())
                .baseRange(in.getRangeForLength())
                .build()
        ).collect(Collectors.toList());
    }
    @Override
    public GetPriceDiffOutDto getTodayAndWeeklyMeanPrice(GetPriceDiffInDto in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;
        QUserCode userCode = QUserCode.userCode;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;

        RegionGroup targetRegionGroup = userGroupCodeRepository.findById(in.getRegionGroupId()).orElseThrow();
        InnerProduct targetInnerProduct = innerProductRepository.findById(in.getTargetInnerProductId()).orElseThrow();
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<Tuple> result = query.select(
                        processedPriceInfo.price.avg()
                        ,processedPriceInfo.baseDate
                )
                .from(processedPriceInfo, userGroupCode, innerProduct, userCode)
                .where(
                        processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                .and(processedPriceInfo.baseRange.eq(BaseRange.DAY))
                                .and(processedPriceInfo.regionInfo.id.eq(userCode.codeDetailName))
                                .and(userGroupCode.eq(targetRegionGroup))
                                .and(userCode.userGroupCode.eq(targetRegionGroup))
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                .and(innerProduct.eq(targetInnerProduct))
                ).groupBy(
                        processedPriceInfo.baseDate
                ).orderBy(
                        processedPriceInfo.baseDate.asc()
                )
                .fetch();
        return GetPriceDiffOutDto.builder()
                .basePrice(result.get(result.size()-1).get(processedPriceInfo.price.avg()).longValue())
                .baseDate(result.get(result.size()-1).get(processedPriceInfo.baseDate))
                .meanPrice(result.stream()
                        .map(element->element.get(processedPriceInfo.price.avg()))
                        .reduce((a, b) -> a+b).get().longValue()/result.size()
                )
                .pastDate(result.get(0).get(processedPriceInfo.baseDate))
                .build();
    }

    @Override
    public List<GetPriceDiffListOutDto> getPriceDiffList(GetPriceDiffListInDto in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QUserGroupCode userGroupCode = QUserGroupCode.userGroupCode;

        RegionGroup targetRegionGroupCode = userGroupCodeRepository.findById(in.getRegionGroupId()).orElseThrow();

        QUserCode userCode = QUserCode.userCode;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);


        List<Tuple> result = query.select(
                        innerProduct
                        ,processedPriceInfo.baseDate
                        ,processedPriceInfo.price.avg()
                )
                .from(innerProduct)
                .join(baseProduct)
                    .on(baseProduct.innerProduct.id.eq(innerProduct.id))
                .leftJoin(processedPriceInfo)
                    .on(processedPriceInfo.baseProduct.id.eq(baseProduct.id))
                .join(userCode)
                    .on(processedPriceInfo.regionInfo.eq(userCode))
                .where(
                        innerProduct.isAvailable.eq(true)
                                ,(processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                        .or(processedPriceInfo.baseDate.isNull())
                                )
                                ,(processedPriceInfo.baseRange.eq(BaseRange.DAY)
                                        .or(processedPriceInfo.baseRange.isNull())
                                )
                                ,(processedPriceInfo.regionInfo.eq(userCode)
                                        .or(processedPriceInfo.regionInfo.isNull())
                                )
                                ,(userCode.userGroupCode.eq(targetRegionGroupCode))
                ).groupBy(
                        innerProduct,
                        processedPriceInfo.baseDate
                )
                .fetch();
        return result.stream().map(element ->
                GetPriceDiffListOutDto.builder()
                    .innerProductId(element.get(innerProduct).getId())
                    .price(Optional.ofNullable(element.get(processedPriceInfo.price.avg())))
                    .baseDate(Optional.ofNullable(element.get(processedPriceInfo.baseDate)))
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public String getMaxBaseDate(String baseDate) {
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<String> result = query.select(processedPriceInfo.baseDate.max())
                .from(processedPriceInfo)
                .where(
                        processedPriceInfo.baseDate.loe(baseDate)
                        , processedPriceInfo.baseRange.eq(BaseRange.DAY)
                        , processedPriceInfo.baseProduct.categoryCode.ne("500")
                ).fetch();
        return result.get(0);
    }
}