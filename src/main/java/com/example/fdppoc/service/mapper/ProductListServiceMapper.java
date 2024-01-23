package com.example.fdppoc.service.mapper;

import com.example.fdppoc.repository.dto.GetTopViewedInnerProductIn;
import com.example.fdppoc.service.dto.GetPopularProductCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductListServiceMapper {
    GetTopViewedInnerProductIn from(GetPopularProductCriteria criteria);
}
