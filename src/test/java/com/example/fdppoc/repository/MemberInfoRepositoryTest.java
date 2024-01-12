package com.example.fdppoc.repository;

import com.example.fdppoc.entity.MemberInfo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@Slf4j
class MemberInfoRepositoryTest {
    @Autowired
    MemberInfoRepository memberInfoRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 멤버삽입조회테스트() {
        memberInfoRepository.save(MemberInfo.builder()
                        .id(20160860L)
                        .businessCode("001")
                        .isAgree(true)
                .build());
        Assertions.assertThat(memberInfoRepository.findMemberInfoByIdAndBusinessCode(20160860L,"001").get().getIsAgree()).isEqualTo(true);
    }

}