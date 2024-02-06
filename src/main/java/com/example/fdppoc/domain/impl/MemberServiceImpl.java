package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.CustomerInterestProduct;
import com.example.fdppoc.domain.entity.CustomerSearchHistory;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.mapper.MemberDomainMapper;
import com.example.fdppoc.infrastructure.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final CustomerSearchHistoryRepository customerSearchHistoryRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final CustomerInterestProductRepository customerInterestProductRepository;
    private final InnerProductRepository innerProductRepository;
    private final UserGroupCodeRepository userGroupCodeRepository;
    private final MemberDomainMapper mapper;
    @Override
    public GetMemberPushInfoResult getMemberPushInfo(GetMemberPushInfoCriteria in){
        MemberInfo member = getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();

        return GetMemberPushInfoResult.builder().isAgree(member.getIsAgree()).build();
    }
    @Override
    @Transactional
    public void setMemberPushInfo(SetMemberPushInfoCriteria in){
        MemberInfo member = getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();
        member.setIsAgree(in.getIsAgree());
    }


    @Transactional
    public void insertProductHistory(InsertProductHistoryCriteria in) {

        customerSearchHistoryRepository.save(CustomerSearchHistory
                .builder()
                .innerProduct(innerProductRepository.findById(in.getInnerProductId()).orElseThrow())
                .memberInfo(memberInfoRepository.findById(in.getMemberInfoId()).orElseThrow())
                .regionGroup(userGroupCodeRepository.findById(in.getRegionGroupCodeId()).orElseThrow())
                .submitTime(LocalDateTime.now())
                .build());
    }
    public GetTopViewProductsResult getTopViewProducts(GetTopViewProductsCriteria in){
        return null;
    }

    @Override
    public GetMemberResult getMember(GetMemberCriteria criteria) {
        Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(criteria.getCustomerId(), criteria.getBusinessCode());
        //log.info("getMember 타입 확인 {}",this.getClass());
        if(result.isEmpty())
            result = Optional.of(memberInfoRepository.save(MemberInfo.builder().isAgree(false)
                    .businessCode(criteria.getBusinessCode()).customerId(criteria.getCustomerId()).build()));
        return GetMemberResult.builder().memberInfo(result.get()).build();
    }


    @Override
    @Transactional
    public GetProductInterestResult getProductInterest(GetProductInterestCriteria in){
        MemberInfo member = getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();
        InnerProduct innerProduct = innerProductRepository.findById(in.getTargetInnerProductId()).orElseThrow();
        Optional<CustomerInterestProduct> result = customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(innerProduct, member);

        return GetProductInterestResult.builder().isAvailable(result.isEmpty()?false:result.get().getIsAvailable())
                .build();
    }
    @Override
    @Transactional
    public void setProductInterest(SetProductInterestCriteria in){
        MemberInfo member = getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();
        InnerProduct targetProduct = innerProductRepository.findById(in.getTargetInnerProductId()).orElseThrow();
        Optional<CustomerInterestProduct> result = customerInterestProductRepository
                .getCustomerInterestProductByInnerProductAndMemberInfo(targetProduct, member);

        CustomerInterestProduct customerInterestProduct = result.orElseGet(() ->
                CustomerInterestProduct.builder()
                        .innerProduct(targetProduct)
                        .memberInfo(member)
                        .isAvailable(in.getIsAvailable())
                        .build()
        );

        customerInterestProduct.setIsAvailable(in.getIsAvailable());
        customerInterestProductRepository.save(customerInterestProduct);
    }
    @Override
    @Transactional
    public List<GetMemberInterestProductsResult> getMemberInterestProducts(GetMemberInterestProductsCriteria in) {
        MemberInfo member = getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build()).getMemberInfo();
        List<CustomerInterestProduct> allProduct = customerInterestProductRepository.findAllByMemberInfoAndIsAvailable(member, true);
        return allProduct.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }

}
