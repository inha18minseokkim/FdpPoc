package com.example.fdppoc.service;

import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.*;
import com.example.fdppoc.service.mapper.MemberServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository memberInfoRepository;
    private final MemberServiceMapper mapper;
    public GetMemberPushInfoOut getMemberPushInfo(GetMemberPushInfoIn in){
        //Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getMemberInfoId(), in.getBusinessCode());
        Optional<MemberInfo> result = getMember(mapper.from(in)).getMemberInfo();

        return GetMemberPushInfoOut.builder().isAgree(result.isEmpty() ? false : result.get().getIsAgree()).build();
    }
    public GetMemberOut getMember(GetMemberIn in){
        Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode(in.getCustomerId(), in.getBusinessCode());
        log.info("getMember 타입 확인 {}",this.getClass());
        return GetMemberOut.builder().memberInfo(result).build();
    }
    @Transactional
    public void setMemberPushInfo(SetMemberPushInfoIn in){
        Optional<MemberInfo> memberInfo = getMember(mapper.from(in)).getMemberInfo();
        if(memberInfo.isEmpty()) {
            memberInfoRepository.save(mapper.toEntity(in));
            return;
        }
        memberInfo.get().setIsAgree(in.getIsAgree());
    }

}
