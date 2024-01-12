package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetMemberPushInfoIn {
    private MemberInfo memberInfo;
}
