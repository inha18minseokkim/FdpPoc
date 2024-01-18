package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Builder
@ToString
public class GetMemberOut {
    private Optional<MemberInfo> memberInfo;
}
