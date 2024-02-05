package com.example.fdppoc.controller;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.ProductDetailControllerMapper;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productDetail")
@RequiredArgsConstructor
@Slf4j
public class ProductDetailController {
    private final ProductService productService;
    private final MemberService memberService;
    private final ProductDetailControllerMapper mapper;
    @GetMapping("/getProductDetail/{targetProductId}/{regionGroupId}") //그냥 React 기반으로 간다면 생각하고 짠것(통 응답에서 3개로 쪼갠 것 중 하나)
    public GetProductDetailResponse getProductDetail(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                     @PathVariable("regionGroupId") UserGroupCode userGroupCode,
                                                     GetProductDetailRequest in
                                                ){
        GetProductPriceResult productPrice = productService.getProductPrice(
                GetProductPriceCriteria.builder()
                        .baseDate(in.getBaseDate())
                        .targetInnerProductId(innerProduct.getId())
                        .rangeForLength(in.getRangeForLength())
                        .rangeForTag(BaseRange.DAY)
                        .regionGroupCodeId(userGroupCode.getId())
                        .customerId(in.getCustomerId())
                        .build()
        );
        return mapper.from(productPrice);
    }
    @GetMapping("/getProductDetailLegacy/{targetProductId}/{regionGroupId}") //CKBFP01000009 상품상세정보조회
    public GetProductDetailLegacyResponse getProductDetailLegacy(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                                 @PathVariable("regionGroupId") UserGroupCode userGroupCode
                                                                ,GetProductDetailLegacyRequest request){
        GetDetailPriceLegacyResult result = productService.getDetailPriceLegacy(GetDetailPriceCriteria.builder()
                        .baseDate(request.getBaseDate())
                        .innerProductId(innerProduct.getId())
                        .customerId(request.getCustomerId())
                        .regionGroupId(userGroupCode.getId())
                .build());

        return mapper.from(result);
    }

    @GetMapping("/getProductInterestInfo/{targetProductId}") //CKBFP01000011 관심식품여부조회
    public GetProductInterestInfoResponse getProductInterestInfo(
            @PathVariable("targetProductId")InnerProduct targetProduct,
            GetProductInterestInfoRequest in){
        GetProductInterestResult result = memberService.getProductInterest(
                GetProductInterestCriteria.builder().targetInnerProductId(targetProduct.getId()).customerId(in.getCustomerId()).build());
        return GetProductInterestInfoResponse.builder().isAvailable(result.getIsAvailable()).innerProductId(targetProduct.getId()).build();
    }
    @PostMapping("/setProductInterestInfo/{targetProductId}") //CKBFP01000012 관심식품등록
    public SetProductInterestResponse setProductInterest(
            @PathVariable("targetProductId")InnerProduct targetProduct
            ,@RequestBody SetProductInterestRequest in){

        memberService.setProductInterest(SetProductInterestCriteria.builder()
                        .customerId(in.getCustomerId())
                        .targetInnerProductId(targetProduct.getId())
                        .isAvailable(in.getIsAvailable())
                .build());
        return SetProductInterestResponse.builder().innerProductId(targetProduct.getId()).isAvailable(in.getIsAvailable()).build();
    }

}
