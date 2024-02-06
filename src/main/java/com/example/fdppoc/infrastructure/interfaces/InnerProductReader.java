package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListInDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductsWithFilterInDto;

import java.util.List;

public interface InnerProductReader {
    List<FindInnerProductWithFilterOutDto> findInnerProductWithFilter(FindInnerProductsWithFilterInDto in);
    List<InnerProduct> findInnerProductList(FindInnerProductListInDto in);
}
