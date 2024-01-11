package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.ProcessedPriceInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProcessedPriceInfoRepositoryMapper {
}
