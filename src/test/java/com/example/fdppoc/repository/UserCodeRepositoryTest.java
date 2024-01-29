package com.example.fdppoc.repository;

import com.example.fdppoc.domain.entity.UserCode;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.infrastructure.repository.UserCodeRepository;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@SpringBootTest
class UserCodeRepositoryTest {
    @Autowired
    UserCodeRepository userCodeRepository;
    @Autowired
    UserGroupCodeRepository groupCodeRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void usercode_insertionTest() {
//        UserGroupCode kamisApiRegion = UserGroupCode.builder()
//                .codeDetailName("KamisApiRegionCode")
//                .description("Kamis Api용 지역코드")
//                .orderSequence(1L)
//                .userCodes(new ArrayList<>())
//                .useInfo(true)
//                .build();
        UserGroupCode kamisApiRegion = groupCodeRepository.findById(1L).get();
//        UserCode seoul = UserCode.builder()
//                .codeDetailName("1101")
//                .description("서울지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
//        UserCode daegu = UserCode.builder()
//                .codeDetailName("2200")
//                .description("대구지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
//        UserCode busan = UserCode.builder()
//                .codeDetailName("2100")
//                .description("부산지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
        UserCode suwon = UserCode.builder()
                .codeDetailName("3111")
                .description("수원지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .userGroupCode(kamisApiRegion)
                .build();
        UserCode yongin = UserCode.builder()
                .codeDetailName("3145")
                .description("용인지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .userGroupCode(kamisApiRegion)
                .build();
        userCodeRepository.save(suwon);
        userCodeRepository.save(yongin);
        kamisApiRegion.getUserCodes().add(yongin);
        kamisApiRegion.getUserCodes().add(suwon);
//        kamisApiRegion.getUserCodes().add(busan);
//        userCodeRepository.save(seoul);
//        userCodeRepository.save(daegu);
//        userCodeRepository.save(busan);
        groupCodeRepository.save(kamisApiRegion);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void 서울지역_삽입() {
        UserGroupCode seoulGroup = UserGroupCode.builder()
                .codeDetailName("KbankRegionCode")
                .description("서울지역코드")
                .orderSequence(1L)
                .userCodes(new ArrayList<>())
                .useInfo(true)
                .build();

        UserGroupCode savedSeoulGroup = groupCodeRepository.save(seoulGroup);

        UserCode seoul = UserCode.builder()
                .codeDetailName("1101")
                .description("서울지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .userGroupCode(savedSeoulGroup)
                .build();

        savedSeoulGroup.getUserCodes().add(seoul);
        UserCode savedSeoul = userCodeRepository.save(seoul);

        savedSeoulGroup = groupCodeRepository.save(savedSeoulGroup);
        Assertions.assertThat(savedSeoulGroup.getUserCodes()).contains(savedSeoul);

//        UserCode daegu = UserCode.builder()
//                .codeDetailName("2200")
//                .description("대구지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
//        UserCode busan = UserCode.builder()
//                .codeDetailName("2100")
//                .description("부산지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();

//        kamisApiRegion.getUserCodes().add(busan);
//        userCodeRepository.save(daegu);
//        userCodeRepository.save(busan);

    }
    @Test
    @Transactional
    @Rollback(value = true)
    void 경기지역_삽입() {
        UserGroupCode gyeongki = UserGroupCode.builder()
                .codeDetailName("KbankRegionCode")
                .description("경기지역코드")
                .orderSequence(1L)
                .userCodes(new ArrayList<>())
                .useInfo(true)
                .build();

        UserGroupCode savedGyonkiGroup = groupCodeRepository.save(gyeongki);

        UserCode yongin = UserCode.builder()
                .codeDetailName("3145")
                .description("용인지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .userGroupCode(savedGyonkiGroup)
                .build();
        UserCode suwon = UserCode.builder()
                .codeDetailName("3111")
                .description("수원지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .userGroupCode(savedGyonkiGroup)
                .build();
        savedGyonkiGroup.getUserCodes().add(yongin);
        savedGyonkiGroup.getUserCodes().add(suwon);
        UserCode savedyongin = userCodeRepository.save(yongin);
        UserCode savedSuwon = userCodeRepository.save(suwon);

        savedGyonkiGroup = groupCodeRepository.save(savedGyonkiGroup);
        Assertions.assertThat(savedGyonkiGroup.getUserCodes()).contains(savedyongin);
        Assertions.assertThat(savedGyonkiGroup.getUserCodes()).contains(savedSuwon);
    }
}