package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetBaseCodesRequest;
import com.example.fdppoc.controller.dto.GetBaseCodesResponseElement;
import com.example.fdppoc.controller.dto.SetBaseCodesRequestElement;
import com.example.fdppoc.service.dto.GetBaseCodesIn;
import com.example.fdppoc.service.dto.GetBaseCodesOut;
import com.example.fdppoc.service.dto.SetBaseCodesIn;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductCodeAdminControllerMapper {
    GetBaseCodesIn from(GetBaseCodesRequest request);
    GetBaseCodesResponseElement from(GetBaseCodesOut element);
    SetBaseCodesIn from(SetBaseCodesRequestElement element);
}
