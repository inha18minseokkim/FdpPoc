package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterIn;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterOut;
import com.example.fdppoc.service.dto.GetBaseCodesCriteria;
import com.example.fdppoc.service.dto.GetBaseCodesResult;
import com.example.fdppoc.service.dto.SetBaseCodesCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BaseProductServiceMapper {
    FindBaseProductWithFilterIn from(GetBaseCodesCriteria in);
    GetBaseCodesResult from(FindBaseProductWithFilterOut in);
    @Mapping(target="innerProduct",ignore = true)
    BaseProduct toEntity(SetBaseCodesCriteria element);
}
