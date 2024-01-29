package com.example.fdppoc.controller;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.code.ControllerResponse;
import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.ProductDetailControllerMapper;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.interfaces.ProductPriceService;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/productDetail")
@RequiredArgsConstructor
@Slf4j
public class ProductDetailController {
    private final ProductPriceService productPriceService;
    private final MemberService memberService;
    private final ProductDetailControllerMapper mapper;
    private final MemberInfoRepository memberInfoRepository;
    @GetMapping("/getProductDetail/{targetProductId}/{regionGroupId}")
    public GetProductDetailResponse getProductDetail(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                     @PathVariable("regionGroupId") UserGroupCode userGroupCode,
                                                     @Validated MemberInfo memberInfo,
                                                     GetProductDetailRequest in
                                                ){

        log.info("사용자 처리 : {}",memberInfo);
        //여기가 맞나? 사용자 없으면 저장 다른 서비스로 분리해야할듯
        Optional<MemberInfo> memberInfoOp = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(memberInfo.getCustomerId(), memberInfo.getBusinessCode());
        if(memberInfoOp.isEmpty())
            memberInfoOp = Optional.of(memberInfoRepository.save(
                    MemberInfo.builder().customerId(memberInfo.getCustomerId()).businessCode(memberInfo.getBusinessCode()).isAgree(false).build())
            );
        memberInfo = memberInfoOp.get();
        //이런 코드가 있는 이유 : asis의 요구사항이 TOBE Spring JPA 사상과 맞지 않다
        log.info(in.getBaseDate());
        GetProductPriceResult productPrice = productPriceService.getProductPrice(
                GetProductPriceCriteria.builder()
                        .baseDate(in.getBaseDate())
                        .targetProduct(innerProduct)
                        .rangeForLength(in.getRangeForLength())
                        .rangeForTag(BaseRange.DAY)
                        .regionGroup(userGroupCode)
                        .memberInfo(memberInfo)
                        .build()
        );
        return mapper.from(productPrice);
    }
    @GetMapping("/getProductInterestInfo/{targetProductId}")
    public GetProductInterestInfoResponse getProductInterestInfo(
            @PathVariable("targetProductId")InnerProduct targetProduct,
            GetProductInterestInfoRequest in){
        log.info("입력 받음 : {}",in);
        //여기가 맞나? 사용자 없으면 저장 다른 서비스로 분리해야할듯
        Optional<MemberInfo> memberInfoOp = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getCustomerId(), "001");
        if(memberInfoOp.isEmpty())
            memberInfoOp = Optional.of(memberInfoRepository.save(
                    MemberInfo.builder().customerId(in.getCustomerId()).businessCode("001").isAgree(false).build())
            );
        MemberInfo memberInfo = memberInfoOp.get();
        //이런 코드가 있는 이유 : asis의 요구사항이 TOBE Spring JPA 사상과 맞지 않다

        GetProductInterestResult result = memberService.getProductInterest(GetProductInterestCriteria.builder().targetProduct(targetProduct).memberInfo(memberInfo).build());
        return GetProductInterestInfoResponse.builder().isAvailable(result.getIsAvailable()).build();
    }
    @PostMapping("/setProductInterestInfo/{targetProductId}")
    public SetProductInterestResponse setProductInterest(
            @PathVariable("targetProductId")InnerProduct baseProduct
            ,@RequestBody SetProductInterestRequest in){
        log.info("받음 : {}",in);
        //여기가 맞나? 사용자 없으면 저장 다른 서비스로 분리해야할듯
        Optional<MemberInfo> memberInfoOp = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getCustomerId(), "001");
        if(memberInfoOp.isEmpty())
            memberInfoOp = Optional.of(memberInfoRepository.save(
                    MemberInfo.builder().customerId(in.getCustomerId()).businessCode("001").isAgree(false).build())
            );
        MemberInfo memberInfo = memberInfoOp.get();
        //이런 코드가 있는 이유 : asis의 요구사항이 TOBE Spring JPA 사상과 맞지 않다
        memberService.setProductInterest(SetProductInterestCriteria.builder()
                        .memberInfo(memberInfo)
                        .targetProduct(baseProduct)
                        .isAvailable(in.getIsAvailable())
                .build());
        return SetProductInterestResponse.builder().responseCode(ControllerResponse.OK).build();
    }

}
