package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.*;

import java.util.List;

public interface InnerCategoryService {
    List<GetAllInnerProductsResult> getAllInnerProducts(GetAllInnerProductsCriteria in);
    List<GetAllInnerCategoryResult> getAllInnerCategory(GetAllInnerCategoryCriteria in);
    void setInnerCategory(List<SetInnerCategoryCriteria> in);


}
