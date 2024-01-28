package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Builder
@ToString
public class GetMemberResult {
    private Optional<MemberInfo> memberInfo;
}
