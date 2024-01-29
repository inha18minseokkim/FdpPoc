package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.infrastructure.repository.dto.FindBaseProductWithFilterIn;
import com.example.fdppoc.infrastructure.repository.dto.FindBaseProductWithFilterOut;
import com.example.fdppoc.domain.dto.GetBaseCodesCriteria;
import com.example.fdppoc.domain.dto.GetBaseCodesResult;
import com.example.fdppoc.domain.dto.SetBaseCodesCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BaseProductDomainMapper {
    FindBaseProductWithFilterIn from(GetBaseCodesCriteria in);
    GetBaseCodesResult from(FindBaseProductWithFilterOut in);
    @Mapping(target="innerProduct",ignore = true)
    BaseProduct toEntity(SetBaseCodesCriteria element);
}
