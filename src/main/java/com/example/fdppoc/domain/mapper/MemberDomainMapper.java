package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.dto.GetMemberInterestProductsResult;
import com.example.fdppoc.domain.entity.CustomerInterestProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.dto.GetMemberCriteria;
import com.example.fdppoc.domain.dto.GetMemberPushInfoCriteria;
import com.example.fdppoc.domain.dto.SetMemberPushInfoCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDomainMapper {
    GetMemberCriteria from(SetMemberPushInfoCriteria in);

    GetMemberCriteria from(GetMemberPushInfoCriteria in);

    @Mapping(target = "id", ignore = true)
    MemberInfo toEntity(SetMemberPushInfoCriteria in);

    @Mapping(target="innerProductId",expression = "java(element.getInnerProduct().getId())")
    GetMemberInterestProductsResult from(CustomerInterestProduct element);
}
