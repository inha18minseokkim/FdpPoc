package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductInDto;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductOutDto;

import java.util.List;

public interface CustomerSearchHistoryRepositoryCustom {
    List<GetTopViewedInnerProductOutDto> getTopViewedInnerProduct(GetTopViewedInnerProductInDto in);
}
