package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterInDto;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;

import java.util.List;

public interface BaseProductRepositoryCustom {
    List<FindBaseProductWithFilterOutDto> findBaseProductWithFilter(FindBaseProductWithFilterInDto in);
}
