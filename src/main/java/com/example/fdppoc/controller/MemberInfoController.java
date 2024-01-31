package com.example.fdppoc.controller;

import com.example.fdppoc.code.ControllerResponse;
import com.example.fdppoc.controller.dto.GetMemberPushInfoResponse;
import com.example.fdppoc.controller.dto.GetmemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoResponse;
import com.example.fdppoc.controller.mapper.MemberInfoControllerMapper;
import com.example.fdppoc.domain.impl.MemberServiceImpl;
import com.example.fdppoc.domain.dto.GetMemberPushInfoResult;
import com.example.fdppoc.domain.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberInfo")
@RequiredArgsConstructor
public class MemberInfoController {
    private final MemberService memberService;
    private final MemberInfoControllerMapper mapper;
    @GetMapping("/getMemberPushInfo")
    GetMemberPushInfoResponse getMemberPushInfo(GetmemberPushInfoRequest request) {
        GetMemberPushInfoResult memberPushInfo = memberService.getMemberPushInfo(mapper.from(request));
        return mapper.from(memberPushInfo);
    }
    @PostMapping("/setMemberPushInfo")
    SetMemberPushInfoResponse setMemberPushInfo(@RequestBody SetMemberPushInfoRequest request){
        memberService.setMemberPushInfo(mapper.from(request));
        return SetMemberPushInfoResponse.builder().responseCode(ControllerResponse.OK).build();
    }
}
