package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.*;

import java.util.List;

public interface InnerProductService {
    List<GetInnerProductsWithFilterResult> getInnerProductsWithFilter(GetInnerProductsWithFilterCriteria in);
    void setInnerProducts(List<SetInnerProductsCriteria> in);
    List<GetInnerProductsResult> getInnerProductList(GetInnerProductsCriteria in);

}
