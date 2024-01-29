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
    @GetMapping("/getProductDetail/{targetProductId}/{regionGroupId}")
    public GetProductDetailResponse getProductDetail(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                     @PathVariable("regionGroupId") UserGroupCode userGroupCode,
                                                     GetProductDetailRequest in
                                                ){
        GetProductPriceResult productPrice = productPriceService.getProductPrice(
                GetProductPriceCriteria.builder()
                        .baseDate(in.getBaseDate())
                        .targetProduct(innerProduct)
                        .rangeForLength(in.getRangeForLength())
                        .rangeForTag(BaseRange.DAY)
                        .regionGroup(userGroupCode)
                        .customerId(in.getCustomerId())
                        .build()
        );
        return mapper.from(productPrice);
    }
    @GetMapping("/getProductInterestInfo/{targetProductId}")
    public GetProductInterestInfoResponse getProductInterestInfo(
            @PathVariable("targetProductId")InnerProduct targetProduct,
            GetProductInterestInfoRequest in){
        GetProductInterestResult result = memberService.getProductInterest(
                GetProductInterestCriteria.builder().targetProduct(targetProduct).customerId(in.getCustomerId()).build());
        return GetProductInterestInfoResponse.builder().isAvailable(result.getIsAvailable()).build();
    }
    @PostMapping("/setProductInterestInfo/{targetProductId}")
    public SetProductInterestResponse setProductInterest(
            @PathVariable("targetProductId")InnerProduct baseProduct
            ,@RequestBody SetProductInterestRequest in){

        memberService.setProductInterest(SetProductInterestCriteria.builder()
                        .customerId(in.getCustomerId())
                        .targetProduct(baseProduct)
                        .isAvailable(in.getIsAvailable())
                .build());
        return SetProductInterestResponse.builder().responseCode(ControllerResponse.OK).build();
    }

}
