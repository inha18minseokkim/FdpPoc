package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.GetBaseCodesCriteria;
import com.example.fdppoc.domain.dto.GetBaseCodesResult;
import com.example.fdppoc.domain.dto.SetBaseCodesCriteria;

import java.util.List;

public interface BaseProductService {
    List<GetBaseCodesResult> getBaseCodes(GetBaseCodesCriteria in);
    void setBaseCodes(List<SetBaseCodesCriteria> in);
}
