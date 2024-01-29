package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.*;

import java.util.List;

public interface ProductPriceService {
    List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria);
    List<GetAllProductResult> getAllProduct(GetAllProductCriteria criteria);
    GetProductPriceResult getProductPrice(GetProductPriceCriteria in);
}
