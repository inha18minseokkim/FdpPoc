package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.service.dto.GetMemberIn;
import com.example.fdppoc.service.dto.GetMemberPushInfoIn;
import com.example.fdppoc.service.dto.SetMemberPushInfoIn;
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
    GetMemberIn from(SetMemberPushInfoIn in);

    GetMemberIn from(GetMemberPushInfoIn in);

    @Mapping(target = "id", ignore = true)
    MemberInfo toEntity(SetMemberPushInfoIn in);
}
