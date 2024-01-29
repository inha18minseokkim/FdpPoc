package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetMemberPushInfoResponse;
import com.example.fdppoc.controller.dto.GetmemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoRequest;
import com.example.fdppoc.domain.dto.GetMemberPushInfoCriteria;
import com.example.fdppoc.domain.dto.GetMemberPushInfoResult;
import com.example.fdppoc.domain.dto.SetMemberPushInfoCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberInfoControllerMapper {
    GetMemberPushInfoCriteria from(GetmemberPushInfoRequest request);

    GetMemberPushInfoResponse from(GetMemberPushInfoResult memberPushInfo);

    SetMemberPushInfoCriteria from(SetMemberPushInfoRequest request);
}
