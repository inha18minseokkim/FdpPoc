package com.example.fdppoc.controller;

import com.example.fdppoc.controller.dto.GetMemberPushInfoResponse;
import com.example.fdppoc.controller.dto.GetmemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoRequest;
import com.example.fdppoc.controller.dto.SetMemberPushInfoResponse;
import com.example.fdppoc.controller.mapper.MemberInfoControllerMapper;
import com.example.fdppoc.domain.dto.GetMemberPushInfoResult;
import com.example.fdppoc.domain.dto.SetMemberPushInfoResult;
import com.example.fdppoc.domain.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberInfo")
@RequiredArgsConstructor
public class MemberInfoController {
    private final MemberService memberService;
    private final MemberInfoControllerMapper mapper;
    @GetMapping("/v1/getMemberPushInfo") //CKBFP01000006 푸시동의조회
    ResponseEntity<GetMemberPushInfoResponse> getMemberPushInfo(GetmemberPushInfoRequest request) {
        GetMemberPushInfoResult memberPushInfo = memberService.getMemberPushInfo(mapper.from(request));
        return ResponseEntity.ok().body(
                mapper.from(memberPushInfo));
    }
    @PostMapping("/v1/setMemberPushInfo") //CKBFP01000005 푸시동의저장
    ResponseEntity<SetMemberPushInfoResponse> setMemberPushInfo(@RequestBody SetMemberPushInfoRequest request){
        SetMemberPushInfoResult result = memberService.setMemberPushInfo(mapper.from(request));
        return ResponseEntity.ok().body(
                mapper.from(result));
    }
}
