package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.infrastructure.dto.GetMemberInDto;

public interface MemberInfoRepositoryCustom {
    MemberInfo getMember(GetMemberInDto in);
}
