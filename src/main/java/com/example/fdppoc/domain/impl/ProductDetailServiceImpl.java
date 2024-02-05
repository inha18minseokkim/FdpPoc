package com.example.fdppoc.domain.impl;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.ProductDetailService;
import com.example.fdppoc.domain.mapper.ProductDetailServiceMapper;
import com.example.fdppoc.infrastructure.dto.FindPriceListByGroupRegionCodeIn;
import com.example.fdppoc.infrastructure.dto.FindPriceListByGroupRegionCodeOut;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffListIn;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffListOut;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.ProcessedPriceInfoRepository;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailServiceImpl implements ProductDetailService {
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final ProcessedPriceInfoRepository processedPriceInfoRepository;
    private final InnerProductRepository innerProductRepository;
    private final ProductDetailServiceMapper mapper;
    @Override
    @Cacheable(value = "ProductDetailServiceImpl.getDetailPriceList", key="#criteria")
    @Transactional
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
            double gapPrice = currentPrice - averagePrice;
            double gapRatio = gapPrice / averagePrice;
            return GetInnerProductPricesResult.builder()
                    .averagePrice(averagePrice)
                    .currentPrice(currentPrice)
                    .gapPrice(gapPrice)
                    .gapRatio(gapRatio)
                    .baseDate(baseDate)
                    .innerProductId(innerProduct.getId())
                    .build();
        }).sorted((a,b) -> a.getGapRatio() < b.getGapRatio() ? 1 : -1).collect(Collectors.toList());
        return priceList;
    }

    @Override
    @Transactional
    @Cacheable(value = "ProductDetailServiceImpl.getDetailPriceList", key="#criteria")
    public List<GetDetailPriceLegacyResultElement> getDetailPriceList(GetDetilPriceListCriteria criteria) {
        InnerProduct innerProduct = innerProductRepository.findById(criteria.getInnerProductId()).orElseThrow();
        UserGroupCode regionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId()).orElseThrow();
        String latestBaseDate = criteria.getBaseDate();
        // 1년치 가격 뽑아옴
        List<FindPriceListByGroupRegionCodeOut> priceList
                = processedPriceInfoRepository.findPriceListByGroupRegionCode
                (FindPriceListByGroupRegionCodeIn.builder()
                        .regionGroup(regionGroup)
                        .baseDate(latestBaseDate)
                        .rangeForLength(BaseRange.YEAR)
                        .rangeForTag(BaseRange.DAY)
                        .targetProduct(innerProduct).build());
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
        return list;
    }
}
