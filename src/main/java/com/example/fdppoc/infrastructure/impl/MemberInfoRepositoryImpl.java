package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.infrastructure.dto.GetMemberInDto;
import com.example.fdppoc.infrastructure.interfaces.MemberInfoRepositoryCustom;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberInfoRepositoryImpl implements MemberInfoRepositoryCustom {
}
