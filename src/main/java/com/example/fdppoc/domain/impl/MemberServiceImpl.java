package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.CustomerInterestProduct;
import com.example.fdppoc.domain.entity.CustomerSearchHistory;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.mapper.MemberDomainMapper;
import com.example.fdppoc.infrastructure.repository.CustomerInterestProductRepository;
import com.example.fdppoc.infrastructure.repository.CustomerSearchHistoryRepository;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
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
    private final MemberInfoRepository memberInfoRepository;
    private final CustomerSearchHistoryRepository customerSearchHistoryRepository;
    private final CustomerInterestProductRepository customerInterestProductRepository;
    private final MemberServiceImpl memberService;
    private final MemberDomainMapper mapper;
    @Override
    public GetMemberPushInfoResult getMemberPushInfo(GetMemberPushInfoCriteria in){
        //Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getMemberInfoId(), in.getBusinessCode());
        Optional<MemberInfo> result = getMember(mapper.from(in)).getMemberInfo();

        return GetMemberPushInfoResult.builder().isAgree(result.isEmpty() ? false : result.get().getIsAgree()).build();
    }
    @Override
    @Transactional
    public GetMemberResult getMember(GetMemberCriteria in){
        Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getCustomerId(), in.getBusinessCode());
        log.info("getMember 타입 확인 {}",this.getClass());
        if(result.isEmpty())
            result = Optional.of(memberInfoRepository.save(MemberInfo.builder().isAgree(false)
                    .businessCode(in.getBusinessCode()).customerId(in.getCustomerId()).build()));
        return GetMemberResult.builder().memberInfo(result).build();
    }
    @Override
    @Transactional
    public void setMemberPushInfo(SetMemberPushInfoCriteria in){
        Optional<MemberInfo> memberInfo = getMember(mapper.from(in)).getMemberInfo();
        if(memberInfo.isEmpty()) {
            memberInfoRepository.save(mapper.toEntity(in));
            return;
        }
        memberInfo.get().setIsAgree(in.getIsAgree());
    }


    @Transactional
    public void insertProductHistory(InsertProductHistoryCriteria in) {
        customerSearchHistoryRepository.save(CustomerSearchHistory
                .builder()
                .innerProduct(in.getInnerProduct())
                .memberInfo(in.getMemberInfo())
                .regionGroup(in.getRegionGroup())
                .submitTime(LocalDateTime.now())
                .build());
    }
    public GetTopViewProductsResult getTopViewProducts(GetTopViewProductsCriteria in){
        return null;
    }



    @Override
    @Transactional
    public GetProductInterestResult getProductInterest(GetProductInterestCriteria in){
        Optional<CustomerInterestProduct> result = customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        return GetProductInterestResult.builder().isAvailable(result.isEmpty()?false:result.get().getIsAvailable())
                .build();
    }
    @Override
    @Transactional
    public void setProductInterest(SetProductInterestCriteria in){
        log.info("setProductInterest : {}",in);
        Optional<CustomerInterestProduct> result = customerInterestProductRepository
                .getCustomerInterestProductByInnerProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        CustomerInterestProduct customerInterestProduct = result.orElseGet(() ->
                CustomerInterestProduct.builder()
                        .innerProduct(in.getTargetProduct())
                        .memberInfo(in.getMemberInfo())
                        .isAvailable(in.getIsAvailable())
                        .build()
        );

        customerInterestProduct.setIsAvailable(in.getIsAvailable());
        customerInterestProductRepository.save(customerInterestProduct);
    }
    @Override
    @Transactional
    public List<GetMemberInterestProductsResult> getMemberInterestProducts(GetMemberInterestProductsCriteria in) {
        GetMemberResult member = memberService.getMember(GetMemberCriteria.builder().customerId(in.getCustomerId()).businessCode("001").build());
        List<CustomerInterestProduct> allProduct = customerInterestProductRepository.findAllByMemberInfoAndIsAvailable(member.getMemberInfo().get(), true);
        return allProduct.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }

}
