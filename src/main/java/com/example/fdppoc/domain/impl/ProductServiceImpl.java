package com.example.fdppoc.domain.impl;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.interfaces.ProductDetailService;
import com.example.fdppoc.domain.interfaces.ProductService;
import com.example.fdppoc.infrastructure.dto.*;
import com.example.fdppoc.infrastructure.repository.*;
import com.example.fdppoc.domain.mapper.ProductListServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final CustomerSearchHistoryRepository customerSearchHistoryRepository;
    private final ProcessedPriceInfoRepository processedPriceInfoRepository;
    private final MemberService memberService;
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final ProductListServiceMapper mapper;
    private final InnerProductRepository innerProductRepository;
    private final ProductDetailService productDetailService;
    //인기상품리스트조회
    @Override
    public List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria){
        List<GetTopViewedInnerProductOut> results = customerSearchHistoryRepository.getTopViewedInnerProduct(mapper.from(criteria));
        Optional<UserGroupCode> targetRegionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId());
        String startDate = LocalDate.parse(criteria.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd")).minusDays(BaseRange.WEEK.getGapDay()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return results.stream().map(element -> {
                    GetPriceDiffOut priceDiff = processedPriceInfoRepository.getTodayAndWeeklyMeanPrice(GetPriceDiffIn.builder()
                            .regionGroup(targetRegionGroup.get()).targetProduct(element.getInnerProduct()).startDate(startDate).endDate(criteria.getBaseDate()).build());
                    log.debug("쿼리결과 : {}",priceDiff);
                    return GetPopularProductResult.builder()
                            .innerProductId(element.getInnerProduct().getId())
                            .count(element.getCount())
                            .gapPrice(priceDiff.getBasePrice()-priceDiff.getMeanPrice())
                            .gapRatio(((double)(priceDiff.getBasePrice()-priceDiff.getMeanPrice())/ priceDiff.getBasePrice()))
                            .build();
                }
        ).collect(Collectors.toList());
    }
    //Legacy View 용 API
    @Override
    @Transactional
    public List<GetAllProductResult> getAllProduct(GetAllProductCriteria criteria){
        //모든 상품 맵 가져옴
        List<InnerProduct> allProduct = innerProductRepository.findAllByIsAvailable(true);
        //가장 많이 조회한 상품 리스트 가져옴
        List<GetTopViewedInnerProductOut> topViewedInnerProducts = customerSearchHistoryRepository.getTopViewedInnerProduct(GetTopViewedInnerProductIn.builder()
                .currentTime(LocalDateTime.now()).rangeHour(12)
                .build());

        //가격 가져올 범위 에서 startDate는 baseDate에서 일주일 전
        String startDate = LocalDate.parse(criteria.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
                .minusDays(6)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //일주일간 가격 통계정보 가져옴
    //Cacheable
        List<GetInnerProductPricesResult> innerProductPriceList = productDetailService.getInnerProductPriceList(GetInnerProductPricesCriteria.builder()
                .regionGroupId(criteria.getRegionGroupId()).endDate(criteria.getBaseDate()).startDate(startDate).build());
        //고객 관심상품정보 가져옴
        List<GetMemberInterestProductsResult> memberInterestProducts = memberService.getMemberInterestProducts(GetMemberInterestProductsCriteria.builder().customerId(criteria.getCustomerId()).build());

        //각각 정보 적재를 위한 맵
        Map<String,GetAllProductResult> resultMap = allProduct.stream().collect(Collectors.toMap(InnerProduct::getId
                ,element -> {
                    return GetAllProductResult.builder()
                            .innerProductId(element.getId())
                            .currentPrice(0L)
                            .innerCategoryId(element.getInnerCategory().getId())
                            .gapPrice(0L)
                            .gapPriceRatio(0.0)
                            .clickCount(0L)
                            .isRiseOrDecline(false)
                            .isCustomerInterest(false)
                            .unitValueName(element.getBaseProducts().get(0).getUnitValue() + element.getBaseProducts().get(0).getUnitName())
                            .innerProductName(element.getProductName())
                            .innerCategoryName(element.getInnerCategory().getInnerCategoryName())
                            .build();
                }));
        //관심상품
        memberInterestProducts.stream().forEach(element -> {
            GetAllProductResult interestProduct = resultMap.get(element.getInnerProductId());
            interestProduct.setIsCustomerInterest(true);
        });
        //검색순위
        log.debug("시작 : {}",resultMap);
        topViewedInnerProducts.forEach(element -> {
            log.debug("중간 {}",element);
            GetAllProductResult clickProduct = resultMap.get(element.getInnerProduct().getId());
            clickProduct.setClickCount(element.getCount());
        });
        //가격정보
        for(int i = 0; i < Math.min(innerProductPriceList.size(),5); i++){
            if(innerProductPriceList.get(i).getGapRatio() >= 0) break;
            String minusInnerProduct = innerProductPriceList.get(i).getInnerProductId();
            resultMap.get(minusInnerProduct).setIsRiseOrDecline(true);
        }
        for(int i = innerProductPriceList.size()-1; i >= 0; i--){
            if(innerProductPriceList.get(i).getGapRatio() <= 0) break;
            String minusInnerProduct = innerProductPriceList.get(i).getInnerProductId();
            resultMap.get(minusInnerProduct).setIsRiseOrDecline(true);
        }
        innerProductPriceList.forEach(element -> {
            GetAllProductResult targetProduct = resultMap.get(element.getInnerProductId());
            targetProduct.setCurrentPrice(Math.round(element.getCurrentPrice()));
            targetProduct.setGapPriceRatio(element.getGapRatio() * 100);
            targetProduct.setGapPrice(Math.round(element.getGapPrice()));
        });
        return resultMap.values().stream().toList();
    }

    //관심상품리스트조회
    //자주먹는상품리스트조회
    //가격변동TOP5 상품리스트조회

    @Override
    @Transactional
    public GetProductPriceResult getProductPrice(GetProductPriceCriteria in){
        List<FindPriceListByGroupRegionCodeOut> dailyPrices = processedPriceInfoRepository.findPriceListByGroupRegionCode(mapper.from(in));

        LongSummaryStatistics summary = dailyPrices.stream()
                .map(element -> element.getPrice()).mapToLong(Long::longValue).summaryStatistics();

        MemberInfo member = memberService.getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();

        memberService.insertProductHistory(InsertProductHistoryCriteria.builder()
                .innerProductId(in.getTargetInnerProductId())
                .memberInfoId(member.getId())
                .regionGroupCodeId(in.getRegionGroupCodeId())
                .build());

        return GetProductPriceResult.builder()
                .meanPrice(Math.round(summary.getAverage()))
                .maximumPrice(summary.getMax())
                .minimumPrice(summary.getMin())
                .priceList(dailyPrices.stream().map(element->element.getPrice()).collect(Collectors.toList()))
                .baseRange(in.getRangeForLength())
                .build();
    }
    @Override
    @Cacheable(value = "ProductServiceImpl.getLatestBaseDate",key = "#criteria")
    public GetLatestBaseDateResult getLatestBaseDate(GetLatestBaseDate criteria) {
        String maxBaseDate = processedPriceInfoRepository.getMaxBaseDate(criteria.getBaseDate());
        return GetLatestBaseDateResult.builder().baseDate(maxBaseDate).build();
    }

    @Override
    @Transactional
    public GetDetailPriceLegacyResult getDetailPriceLegacy(GetDetailPriceCriteria criteria) {
        GetLatestBaseDateResult latestBaseDate = getLatestBaseDate(GetLatestBaseDate
                                                                .builder().baseDate(criteria.getBaseDate()).build());

        Optional<UserGroupCode> regionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId());
        Optional<InnerProduct> innerProduct = innerProductRepository.findById(criteria.getInnerProductId());
        GetMemberResult member = memberService.getMember(GetMemberCriteria.builder()
                                                        .customerId(criteria.getCustomerId())
                                                        .businessCode("001").build());
        //멤버 조회 이력 insert
        memberService.insertProductHistory(InsertProductHistoryCriteria.builder()
                                        .innerProductId(criteria.getInnerProductId())
                                        .regionGroupCodeId(criteria.getRegionGroupId())
                                        .memberInfoId(member.getMemberInfo().getId())
                                        .build());
        //가격정보 기간별로 가져옴
        List<GetDetailPriceLegacyResultElement> list = productDetailService.getDetailPriceList(GetDetilPriceListCriteria.builder()
                        .innerProductId(criteria.getInnerProductId())
                        .regionGroupId(criteria.getRegionGroupId())
                        .baseDate(latestBaseDate.getBaseDate())
                .build());

        return GetDetailPriceLegacyResult.builder()
                .listCount((long)list.size())
                .unitName(innerProduct.get().getBaseProducts().get(0).getUnitName())
                .unitValue(innerProduct.get().getBaseProducts().get(0).getUnitValue().doubleValue())
                .innerProductId(innerProduct.get().getId())
                .regionGroupId(regionGroup.get().getId())
                .gapPrice(null)
                .basePrice(null)
                .innerProductName(innerProduct.get().getProductName())
                .baseDate(latestBaseDate.getBaseDate())
                .list(list)
                .build();
    }


}
