package com.example.fdppoc.service;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.*;
import com.example.fdppoc.repository.dto.*;
import com.example.fdppoc.service.dto.GetAllProductCriteria;
import com.example.fdppoc.service.dto.GetAllProductResult;
import com.example.fdppoc.service.dto.GetPopularProductCriteria;
import com.example.fdppoc.service.dto.GetPopularProductResult;
import com.example.fdppoc.service.mapper.ProductListServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListService {
    private final CustomerSearchHistoryRepositoryCustom customerSearchHistoryRepositoryCustom;
    private final ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final ProductListServiceMapper mapper;

    //인기상품리스트조회
    public List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria){
        List<GetTopViewedInnerProductOut> results = customerSearchHistoryRepositoryCustom.getTopViewedInnerProduct(mapper.from(criteria));
        Optional<UserGroupCode> targetRegionGroup = userGroupCodeRepository.findById(criteria.getRegionGroupId());
        String startDate = LocalDate.parse(criteria.getBaseDate(), DateTimeFormatter.ofPattern("yyyyMMdd")).minusDays(BaseRange.WEEK.getGapDay()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return results.stream().map(element -> {

            GetPriceDiffOut priceDiff = processedPriceInfoRepositoryCustom.getTodayAndWeeklyMeanPrice(GetPriceDiffIn.builder()
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
    public List<GetAllProductResult> getAllProduct(GetAllProductCriteria criteria){
        Map<Long, InnerProduct> allProduct = processedPriceInfoRepositoryCustom.getAllProduct(GetAllProductIn.builder().build());
        List<GetTopViewedInnerProductOut> topViewedInnerProducts = customerSearchHistoryRepositoryCustom.getTopViewedInnerProduct(GetTopViewedInnerProductIn.builder()
                .currentTime(LocalDateTime.now()).rangeHour(12)
                .build());

        UserGroupCode regionGroupId = userGroupCodeRepository.findById(criteria.getRegionGroupId()).orElseThrow();
        Optional<MemberInfo> memberInfo = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(criteria.getCustomerId(), "001");
        if(memberInfo.isEmpty())
            memberInfo = Optional.of(memberInfoRepository.save(MemberInfo.builder().isAgree(false).customerId(criteria.getCustomerId()).businessCode("001").build()));

        return null;
    }
    //관심상품리스트조회
    //자주먹는상품리스트조회
    //가격변동TOP5 상품리스트조회



}
