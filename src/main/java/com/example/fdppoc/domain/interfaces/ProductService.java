package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.*;

import java.util.List;

public interface ProductService {
    List<GetPopularProductResult> getPopularProduct(GetPopularProductCriteria criteria);
    List<GetAllProductResult> getAllProduct(GetAllProductCriteria criteria);
    GetProductPriceResult getProductPrice(GetProductPriceCriteria in);
    GetLatestBaseDateResult getLatestBaseDate(GetLatestBaseDate criteria);
    GetDetailPriceLegacyResult getDetailPriceLegacy(GetDetailPriceCriteria criteria);
}
