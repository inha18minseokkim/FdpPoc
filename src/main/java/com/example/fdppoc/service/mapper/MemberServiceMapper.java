package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.service.dto.GetMemberCriteria;
import com.example.fdppoc.service.dto.GetMemberPushInfoCriteria;
import com.example.fdppoc.service.dto.SetMemberPushInfoCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberServiceMapper {
    GetMemberCriteria from(SetMemberPushInfoCriteria in);

    GetMemberCriteria from(GetMemberPushInfoCriteria in);

    @Mapping(target = "id", ignore = true)
    MemberInfo toEntity(SetMemberPushInfoCriteria in);
}
