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
        QRegionGroup regionGroup = QRegionGroup.regionGroup;
        QRegion region = QRegion.region;
        QBaseProduct baseProduct = QBaseProduct.baseProduct;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;
        String startDate = LocalDate.parse(in.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
                .minusDays(in.getRangeForLength().getGapDay()-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<Tuple> results = query.select(
                processedPriceInfo.baseDate,
                processedPriceInfo.price.avg(),
                regionGroup,
                innerProduct
                )
                .from(processedPriceInfo, regionGroup,innerProduct,region)
                .where(
                        processedPriceInfo.baseRange.eq(in.getRangeForTag())
                                .and(processedPriceInfo.baseDate.between(startDate, in.getBaseDate()))
                                .and(regionGroup.id.eq(in.getRegionGroupId()))
                                .and(processedPriceInfo.region.id.eq(region.regionDetailName))
                                .and(regionGroup.id.eq(region.regionGroup.id)
                                .and(processedPriceInfo.baseProduct.in(innerProduct.baseProducts))
                                                .and(innerProduct.id.eq(in.getTargetInnerProductId()))
                                )
                ).groupBy(
                        processedPriceInfo.baseDate,
                        regionGroup,
                        innerProduct
                )
                .fetch();
        return results.stream().map((element) -> FindPriceListByGroupRegionCodeOut.builder()
                .baseDate(in.getBaseDate())
                .price(element.get(processedPriceInfo.price.avg()).longValue())
                .regionGroupId(element.get(regionGroup).getId())
                .innerProductId(element.get(innerProduct).getId())
                .baseRange(in.getRangeForLength())
                .build()
        ).collect(Collectors.toList());
    }
    @Override
    public GetPriceDiffOutDto getTodayAndWeeklyMeanPrice(GetPriceDiffInDto in){
        QProcessedPriceInfo processedPriceInfo = QProcessedPriceInfo.processedPriceInfo;
        QRegionGroup regionGroup = QRegionGroup.regionGroup;
        QRegion region = QRegion.region;
        QInnerProduct innerProduct = QInnerProduct.innerProduct;

        RegionGroup targetRegionGroup = userGroupCodeRepository.findById(in.getRegionGroupId()).orElseThrow();
        InnerProduct targetInnerProduct = innerProductRepository.findById(in.getTargetInnerProductId()).orElseThrow();
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        List<Tuple> result = query.select(
                        processedPriceInfo.price.avg()
                        ,processedPriceInfo.baseDate
                )
                .from(processedPriceInfo, regionGroup, innerProduct, region)
                .where(
                        processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                .and(processedPriceInfo.baseRange.eq(BaseRange.DAY))
                                .and(processedPriceInfo.region.id.eq(region.regionDetailName))
                                .and(regionGroup.eq(targetRegionGroup))
                                .and(region.regionGroup.eq(targetRegionGroup))
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
        RegionGroup targetRegionGroupCode = userGroupCodeRepository.findById(in.getRegionGroupId()).orElseThrow();

        QRegion region = QRegion.region;
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
                .join(region)
                    .on(processedPriceInfo.region.eq(region))
                .where(
                        innerProduct.isAvailable.eq(true)
                                ,(processedPriceInfo.baseDate.between(in.getStartDate(), in.getEndDate())
                                        .or(processedPriceInfo.baseDate.isNull())
                                )
                                ,(processedPriceInfo.baseRange.eq(BaseRange.DAY)
                                        .or(processedPriceInfo.baseRange.isNull())
                                )
                                ,(processedPriceInfo.region.eq(region)
                                        .or(processedPriceInfo.region.isNull())
                                )
                                ,(region.regionGroup.eq(targetRegionGroupCode))
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