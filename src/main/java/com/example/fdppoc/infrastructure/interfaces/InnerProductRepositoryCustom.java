package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListIn;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.infrastructure.dto.FindInnerProductsWithFilterIn;

import java.util.List;

public interface InnerProductRepositoryCustom {
    List<FindInnerProductWithFilterOut> findInnerProductWithFilter(FindInnerProductsWithFilterIn in);
    List<InnerProduct> findInnerProductList(FindInnerProductListIn in);
}
