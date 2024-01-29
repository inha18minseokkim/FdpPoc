package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductIn;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductOut;

import java.util.List;

public interface CustomerSearchHistoryRepositoryCustom {
    List<GetTopViewedInnerProductOut> getTopViewedInnerProduct(GetTopViewedInnerProductIn in);
}
