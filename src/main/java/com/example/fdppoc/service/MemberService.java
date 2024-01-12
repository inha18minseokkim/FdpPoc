package com.example.fdppoc.service;

import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.GetMemberPushInfoIn;
import com.example.fdppoc.service.dto.GetMemberPushInfoOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository memberInfoRepository;

    public GetMemberPushInfoOut getMemberPushInfo(GetMemberPushInfoIn in){
        return null;
    }

}
