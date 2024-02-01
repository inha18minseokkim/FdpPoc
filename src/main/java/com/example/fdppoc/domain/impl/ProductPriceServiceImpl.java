package com.example.fdppoc.domain.impl;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.interfaces.ProductPriceService;
import com.example.fdppoc.infrastructure.dto.*;
import com.example.fdppoc.infrastructure.impl.CustomerSearchHistoryRepositoryImpl;
import com.example.fdppoc.infrastructure.impl.ProcessedPriceInfoRepositoryImpl;
import com.example.fdppoc.infrastructure.repository.*;
import com.example.fdppoc.domain.mapper.ProductListServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductPriceServiceImpl implements ProductPriceService {
    private final CustomerSearchHistoryRepository customerSearchHistoryRepository;
    private final ProcessedPriceInfoRepository processedPriceInfoRepository;
    private final MemberService memberService;
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final ProductListServiceMapper mapper;
    private final InnerProductRepository innerProductRepository;
    //인기상품리스트조회
    @Override
    public List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria){
        List<GetTopViewedInnerProductOut> results = customerSearchHistoryRepository.getTopViewedInnerProduct(mapper.from(criteria));
        Optional<UserGroupCode> targetRegionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId());
        String startDate = LocalDate.parse(criteria.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd")).minusDays(BaseRange.WEEK.getGapDay()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return results.stream().map(element -> {
                    GetPriceDiffOut priceDiff = processedPriceInfoRepository.getTodayAndWeeklyMeanPrice(GetPriceDiffIn.builder()
                            .regionGroup(targetRegionGroup.get()).targetProduct(element.getInnerProduct()).startDate(startDate).endDate(criteria.getBaseDate()).build());
                    log.info("쿼리결과 : {}",priceDiff);
                    return GetPopularProductResult.builder()
                            .innerProduct(element.getInnerProduct())
                            .count(element.getCount())
                            .gapPrice(priceDiff.getBasePrice()-priceDiff.getMeanPrice())
                            .gapRatio(((double)(priceDiff.getBasePrice()-priceDiff.getMeanPrice())/ priceDiff.getBasePrice()))
                            .build();
                }
        ).collect(Collectors.toList());
    }
    //Legacy View 용 API
    @Override
    public List<GetAllProductResult> getAllProduct(GetAllProductCriteria criteria){
        //모든 상품 맵 가져옴
        List<InnerProduct> allProduct = innerProductRepository.findAllByIsAvailable(true);
        //가장 많이 조회한 상품 리스트 가져옴
        List<GetTopViewedInnerProductOut> topViewedInnerProducts = customerSearchHistoryRepository.getTopViewedInnerProduct(GetTopViewedInnerProductIn.builder()
                .currentTime(LocalDateTime.now()).rangeHour(12)
                .build());
        //가격 가져올건데 대상 지역그룹가져옴
        UserGroupCode regionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId()).orElseThrow();

        //가격 가져올 범위 에서 startDate는 baseDate에서 일주일 전
        String startDate = LocalDate.parse(criteria.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))
                .minusDays(6)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //일주일간 가격 통계정보 가져옴
        List<GetInnerProductPricesResult> innerProductPriceList = getInnerProductPriceList(GetInnerProductPricesCriteria.builder()
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
            GetAllProductResult interestProduct = resultMap.get(element.getInnerProduct().getId());
            interestProduct.setIsCustomerInterest(true);
        });
        //검색순위
        log.info("시작 : {}",resultMap);
        topViewedInnerProducts.forEach(element -> {
            log.info("중간 {}",element);
            GetAllProductResult clickProduct = resultMap.get(element.getInnerProduct().getId());
            clickProduct.setClickCount(element.getCount());
        });
        //가격정보
        for(int i = 0; i < Math.min(innerProductPriceList.size(),5); i++){
            if(innerProductPriceList.get(i).getGapRatio() >= 0) break;
            InnerProduct minusInnerProduct = innerProductPriceList.get(i).getInnerProduct();
            resultMap.get(minusInnerProduct.getId()).setIsRiseOrDecline(true);
        }
        for(int i = innerProductPriceList.size()-1; i >= 0; i--){
            if(innerProductPriceList.get(i).getGapRatio() <= 0) break;
            InnerProduct minusInnerProduct = innerProductPriceList.get(i).getInnerProduct();
            resultMap.get(minusInnerProduct.getId()).setIsRiseOrDecline(true);
        }
        innerProductPriceList.forEach(element -> {
            GetAllProductResult targetProduct = resultMap.get(element.getInnerProduct().getId());
            targetProduct.setCurrentPrice(Math.round(element.getCurrentPrice()));
            targetProduct.setGapPriceRatio(element.getGapRatio() * 100);
            targetProduct.setGapPrice(Math.round(element.getGapPrice()));
        });
        return resultMap.values().stream().toList();
    }
    public List<GetInnerProductPricesResult> getInnerProductPriceList(GetInnerProductPricesCriteria criteria) {
        UserGroupCode regionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId()).orElseThrow();
        List<GetPriceDiffListOut> priceDiffList = processedPriceInfoRepository.getPriceDiffList(
                GetPriceDiffListIn.builder().regionGroup(regionGroup)
                        .startDate(criteria.getStartDate()).endDate(criteria.getEndDate())
                        .build());
        Map<InnerProduct, List<GetPriceDiffListOut>> collect = priceDiffList.parallelStream().collect(Collectors.groupingBy(element -> element.getInnerProduct()));
        List<GetInnerProductPricesResult> priceList = collect.keySet().stream().map(collect::get).map(element -> {
            //기준일자
            Optional<GetPriceDiffListOut> baseDatePriceInfo = element.stream().max(Comparator.comparing(elem -> elem.getBaseDate().orElse("")));
            //상품정보
            InnerProduct innerProduct = baseDatePriceInfo.get().getInnerProduct();
            //현재가격
            Double currentPrice = baseDatePriceInfo.get().getPrice().orElse(0.0);
            String baseDate = baseDatePriceInfo.get().getBaseDate().orElse(criteria.getEndDate());
            //평균가격
            Double averagePrice = element.stream().collect(Collectors.averagingDouble(ele -> ele.getPrice().orElse(0.0)));
            double gapPrice = averagePrice - currentPrice;
            double gapRatio = gapPrice / averagePrice;
            return GetInnerProductPricesResult.builder()
                    .averagePrice(averagePrice)
                    .currentPrice(currentPrice)
                    .gapPrice(gapPrice)
                    .gapRatio(gapRatio)
                    .baseDate(baseDate)
                    .innerProduct(innerProduct)
                    .build();
        }).sorted((a,b) -> a.getGapRatio() < b.getGapRatio() ? 1 : -1).collect(Collectors.toList());
        return priceList;
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
                .innerProduct(in.getTargetProduct())
                .memberInfo(member)
                .regionGroup(in.getRegionGroup())
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
    public GetLatestBaseDateResult getLatestBaseDate(GetLatestBaseDate criteria) {
        String maxBaseDate = processedPriceInfoRepository.getMaxBaseDate(criteria.getBaseDate());
        return GetLatestBaseDateResult.builder().baseDate(maxBaseDate).build();
    }

    @Override
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
                                        .innerProduct(innerProduct.get())
                                        .regionGroup(regionGroup.get())
                                        .memberInfo(member.getMemberInfo())
                                        .build());
        // 1년치 가격 뽑아옴
        List<FindPriceListByGroupRegionCodeOut> priceList
                = processedPriceInfoRepository.findPriceListByGroupRegionCode
                                (FindPriceListByGroupRegionCodeIn.builder()
                                                .regionGroup(regionGroup.get())
                                                .baseDate(latestBaseDate.getBaseDate())
                                                .rangeForLength(BaseRange.YEAR)
                                                .rangeForTag(BaseRange.DAY)
                                                .targetProduct(innerProduct.get()).build());
        //조립시작, 1주일, 1개월, 6개월, 1년
        List<GetDetailPriceLegacyResultElement> list = new ArrayList<>();
        for(BaseRange baseRange : BaseRange.getDetailRangeList()){
            DoubleSummaryStatistics summary = priceList.stream().limit(baseRange.getGapDay()).mapToDouble(element -> element.getPrice()).summaryStatistics();
            List<GetDetailPriceLegacyResultSubElement> subList = priceList.stream().limit(baseRange.getGapDay()).map(element -> mapper.toSubElement(element)).collect(Collectors.toList());
            double max = summary.getMax();
            double min = summary.getMin();
            double average = summary.getAverage();
            list.add(GetDetailPriceLegacyResultElement.builder()
                            .basePrice(average)
                            .baseRange(baseRange)
                            .list(subList)
                            .listSize((long)subList.size())
                            .maximumPrice(max)
                            .minimumPrice(min)
                    .build());
        }

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
