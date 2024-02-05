package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.GetDetailPriceLegacyResultElement;
import com.example.fdppoc.domain.dto.GetDetilPriceListCriteria;
import com.example.fdppoc.domain.dto.GetInnerProductPricesCriteria;
import com.example.fdppoc.domain.dto.GetInnerProductPricesResult;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;

import java.util.List;

public interface ProductDetailService {
    List<GetDetailPriceLegacyResultElement> getDetailPriceList(GetDetilPriceListCriteria criteria);
    List<GetInnerProductPricesResult> getInnerProductPriceList(GetInnerProductPricesCriteria criteria);

}
