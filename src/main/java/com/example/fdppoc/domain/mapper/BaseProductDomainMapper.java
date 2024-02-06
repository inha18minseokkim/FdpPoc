package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterInDto;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;
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
    FindBaseProductWithFilterInDto from(GetBaseCodesCriteria in);
    GetBaseCodesResult from(FindBaseProductWithFilterOutDto in);
    @Mapping(target="innerProduct",ignore = true)
    BaseProduct toEntity(SetBaseCodesCriteria element);
}
