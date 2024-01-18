package com.example.fdppoc.service;

import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberInfoRepository memberInfoRepository;

    @Test
    void 사용자조회() {
        GetMemberIn in = GetMemberIn.builder().customerId("20160860").businessCode("001").build();
        GetMemberOut member = memberService.getMember(in);
        log.info("ㅇㅇㅇ : {} ",memberService.getClass());
        log.info("실행결과 : {}",member.getMemberInfo().get());

        Optional<MemberInfo> repositoryMember = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20160860", "001");
        Assertions.assertThat(member.getMemberInfo().get()).isEqualTo(repositoryMember.get());

    }

    @Test
    void 사용자푸시여부조회() {
        GetMemberPushInfoIn input = GetMemberPushInfoIn.builder()
                .businessCode("001")
                .customerId("20160860")
                .build();
        GetMemberPushInfoOut memberPushInfo = memberService.getMemberPushInfo(input);
        log.info("실행 결과 : {}",memberPushInfo);
        Optional<MemberInfo> repositoryMember = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20160860", "001");
        Assertions.assertThat(memberPushInfo.getIsAgree()).isEqualTo(repositoryMember.get().getIsAgree());
    }
    @Test
    @Transactional
    void 사용자푸시수정() {
        Boolean toBe = false;
        SetMemberPushInfoIn in = SetMemberPushInfoIn.builder()
                .businessCode("001")
                .customerId("20160860")
                .isAgree(toBe)
                .build();
        memberService.setMemberPushInfo(in);
        Optional<MemberInfo> result = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20160860", "001");
        log.info("실행 결과 : {} ",in);
        Assertions.assertThat(result.get().getIsAgree()).isEqualTo(toBe);
    }

}