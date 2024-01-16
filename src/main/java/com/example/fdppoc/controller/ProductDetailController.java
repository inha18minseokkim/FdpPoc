package com.example.fdppoc.controller;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.code.ControllerResponse;
import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.ProductDetailControllerMapper;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.CustomerInterestProductService;
import com.example.fdppoc.service.ProductDetailService;
import com.example.fdppoc.service.dto.*;
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
    private final ProductDetailService productDetailService;
    private final CustomerInterestProductService customerInterestProductService;
    private final ProductDetailControllerMapper mapper;
    private final MemberInfoRepository memberInfoRepository;
    @GetMapping("/getProductDetail/{targetProductId}/{regionGroupId}")
    public GetProductDetailResponse getProductDetail(@PathVariable("targetProductId") BaseProduct baseProduct,
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

        GetProductPriceOut productPrice = productDetailService.getProductPrice(
                GetProductPriceIn.builder()
                        .baseDate(in.getBaseDate())
                        .targetProduct(baseProduct)
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
            @PathVariable("targetProductId")BaseProduct targetProduct,
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

        GetProductInterestOut result = customerInterestProductService.getProductInterest(GetProductInterestIn.builder().targetProduct(targetProduct).memberInfo(memberInfo).build());
        return GetProductInterestInfoResponse.builder().isAvailable(result.getIsAvailable()).build();
    }
    @PostMapping("/setProductInterestInfo/{targetProductId}")
    public SetProductInterestResponse setProductInterest(SetProductInterestRequest in){
        //여기가 맞나? 사용자 없으면 저장 다른 서비스로 분리해야할듯
        Optional<MemberInfo> memberInfoOp = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getCustomerId(), "001");
        if(memberInfoOp.isEmpty())
            memberInfoOp = Optional.of(memberInfoRepository.save(
                    MemberInfo.builder().customerId(in.getCustomerId()).businessCode("001").isAgree(false).build())
            );
        MemberInfo memberInfo = memberInfoOp.get();
        //이런 코드가 있는 이유 : asis의 요구사항이 TOBE Spring JPA 사상과 맞지 않다
        customerInterestProductService.setProductInterest(SetProductInterestIn.builder()
                        .memberInfo(memberInfo)
                        .targetProduct(in.getBaseProduct())
                        .isAvailable(in.getIsAvailable())
                .build());
        return SetProductInterestResponse.builder().responseCode(ControllerResponse.OK).build();
    }

}
