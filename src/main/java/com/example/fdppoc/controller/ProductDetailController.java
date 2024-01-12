package com.example.fdppoc.controller;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.controller.dto.GetProductDetailIn;
import com.example.fdppoc.controller.dto.GetProductDetailOut;
import com.example.fdppoc.controller.mapper.ProductDetailControllerMapper;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.service.ProductDetailService;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productDetail")
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailService;
    private final ProductDetailControllerMapper mapper;

    @GetMapping("/{targetProductId}/{regionGroupId}/getProductDetail")
    public GetProductDetailOut getProductDetail(@PathVariable("targetProductId") BaseProduct baseProduct,
                                                @PathVariable("regionGroupId") UserGroupCode userGroupCode,
                                                GetProductDetailIn in
                                                ){
        GetProductPriceOut productPrice = productDetailService.getProductPrice(
                GetProductPriceIn.builder()
                        .baseDate(in.getBaseDate())
                        .targetProduct(baseProduct)
                        .rangeForLength(in.getRangeForLength())
                        .rangeForTag(BaseRange.DAY)
                        .regionGroup(userGroupCode)
                        .build()
        );
        return mapper.from(productPrice);

    }
}
