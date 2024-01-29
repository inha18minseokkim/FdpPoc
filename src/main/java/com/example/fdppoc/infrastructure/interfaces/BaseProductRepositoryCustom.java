package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterIn;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOut;

import java.util.List;

public interface BaseProductRepositoryCustom {
    List<FindBaseProductWithFilterOut> findBaseProductWithFilter(FindBaseProductWithFilterIn in);
}
