package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.MemberInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Builder
@ToString
public class GetMemberResult {
    private MemberInfo memberInfo;
}
