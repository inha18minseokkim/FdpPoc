package com.example.fdppoc.controller;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.controller.mapper.ProductDetailControllerMapper;
import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.RegionGroup;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.domain.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productDetail")
@RequiredArgsConstructor
@Slf4j
public class ProductDetailController {
    private final ProductService productService;
    private final MemberService memberService;
    private final ProductDetailControllerMapper mapper;
    @GetMapping("/v1/getProductDetail/{targetProductId}/{regionGroupId}") //그냥 React 기반으로 간다면 생각하고 짠것(통 응답에서 3개로 쪼갠 것 중 하나)
    public ResponseEntity<GetProductDetailResponse> getProductDetail(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                                    @PathVariable("regionGroupId") RegionGroup regionGroup,
                                                                    GetProductDetailRequest in
                                                ){
        GetProductPriceResult productPrice = productService.getProductPrice(
                GetProductPriceCriteria.builder()
                        .baseDate(in.getBaseDate())
                        .targetInnerProductId(innerProduct.getId())
                        .rangeForLength(in.getRangeForLength())
                        .rangeForTag(BaseRange.DAY)
                        .regionGroupId(regionGroup.getId())
                        .customerId(in.getCustomerId())
                        .build()
        );
        return ResponseEntity.ok().body(mapper.from(productPrice));
    }
    @GetMapping("/v1/getProductDetailLegacy/{targetProductId}/{regionGroupId}") //CKBFP01000009 상품상세정보조회
    public ResponseEntity<GetProductDetailLegacyResponse> getProductDetailLegacy(@PathVariable("targetProductId") InnerProduct innerProduct,
                                                                 @PathVariable("regionGroupId") RegionGroup regionGroup
                                                                ,GetProductDetailLegacyRequest request){
        GetDetailPriceLegacyResult result = productService.getDetailPriceLegacy(GetDetailPriceCriteria.builder()
                        .baseDate(request.getBaseDate())
                        .innerProductId(innerProduct.getId())
                        .customerId(request.getCustomerId())
                        .regionGroupId(regionGroup.getId())
                .build());

        return ResponseEntity.ok().body(mapper.from(result));
    }

    @GetMapping("/v1/getProductInterestInfo/{targetProductId}") //CKBFP01000011 관심식품여부조회
    public ResponseEntity<GetProductInterestInfoResponse> getProductInterestInfo(
            @PathVariable("targetProductId")InnerProduct targetProduct,
            GetProductInterestInfoRequest in){
        GetProductInterestResult result = memberService.getProductInterest(
                GetProductInterestCriteria.builder().targetInnerProductId(targetProduct.getId()).customerId(in.getCustomerId()).build());
        return ResponseEntity.ok().body(
                GetProductInterestInfoResponse.builder()
                        .isAvailable(result.getIsAvailable())
                        .innerProductId(targetProduct.getId()).build());
    }
    @PostMapping("/v1/setProductInterestInfo/{targetProductId}") //CKBFP01000012 관심식품등록
    public ResponseEntity<SetProductInterestResponse> setProductInterest(
            @PathVariable("targetProductId")InnerProduct targetProduct
            ,@RequestBody SetProductInterestRequest in){

        memberService.setProductInterest(SetProductInterestCriteria.builder()
                        .customerId(in.getCustomerId())
                        .targetInnerProductId(targetProduct.getId())
                        .isAvailable(in.getIsAvailable())
                .build());
        return ResponseEntity.ok().body(SetProductInterestResponse.builder()
                .innerProductId(targetProduct.getId())
                .isAvailable(in.getIsAvailable()).build());
    }

}
