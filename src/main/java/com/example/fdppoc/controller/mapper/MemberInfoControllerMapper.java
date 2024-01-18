package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetMemberPushInfoResponse;
import com.example.fdppoc.controller.dto.GetmemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoRequest;
import com.example.fdppoc.service.dto.GetMemberPushInfoIn;
import com.example.fdppoc.service.dto.GetMemberPushInfoOut;
import com.example.fdppoc.service.dto.SetMemberPushInfoIn;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberInfoControllerMapper {
    GetMemberPushInfoIn from(GetmemberPushInfoRequest request);

    GetMemberPushInfoResponse from(GetMemberPushInfoOut memberPushInfo);

    SetMemberPushInfoIn from(SetMemberPushInfoRequest request);
}
