package com.example.fdppoc.repository;

import com.example.fdppoc.domain.entity.Region;
import com.example.fdppoc.domain.entity.RegionGroup;
import com.example.fdppoc.infrastructure.repository.UserCodeRepository;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
class RegionRepositoryTest {
    @Autowired
    UserCodeRepository userCodeRepository;
    @Autowired
    UserGroupCodeRepository groupCodeRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void usercode_insertionTest() {
//        UserGroupCode kamisApiRegion = UserGroupCode.builder()
//                .codeDetailName("KamisApiRegionCode")
//                .description("Kamis Api용 지역코드")
//                .orderSequence(1L)
//                .userCodes(new ArrayList<>())
//                .useInfo(true)
//                .build();
        RegionGroup kamisApiRegion = groupCodeRepository.findById("FDPREGN1101").get();
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
        Region suwon = Region.builder()
                .codeDetailName("3111")
                .description("수원지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .regionGroup(kamisApiRegion)
                .build();
        Region yongin = Region.builder()
                .codeDetailName("3145")
                .description("용인지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .regionGroup(kamisApiRegion)
                .build();
        userCodeRepository.save(suwon);
        userCodeRepository.save(yongin);
        kamisApiRegion.getRegions().add(yongin);
        kamisApiRegion.getRegions().add(suwon);
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
        RegionGroup seoulGroup = RegionGroup.builder()
                .codeDetailName("KbankRegionCode")
                .description("서울지역코드")
                .orderSequence(1L)
                .regions(new ArrayList<>())
                .useInfo(true)
                .build();

        RegionGroup savedSeoulGroup = groupCodeRepository.save(seoulGroup);

        Region seoul = Region.builder()
                .codeDetailName("1101")
                .description("서울지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .regionGroup(savedSeoulGroup)
                .build();

        savedSeoulGroup.getRegions().add(seoul);
        Region savedSeoul = userCodeRepository.save(seoul);

        savedSeoulGroup = groupCodeRepository.save(savedSeoulGroup);
        Assertions.assertThat(savedSeoulGroup.getRegions()).contains(savedSeoul);

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
        RegionGroup gyeongki = RegionGroup.builder()
                .codeDetailName("KbankRegionCode")
                .description("경기지역코드")
                .orderSequence(1L)
                .regions(new ArrayList<>())
                .useInfo(true)
                .build();

        RegionGroup savedGyonkiGroup = groupCodeRepository.save(gyeongki);

        Region yongin = Region.builder()
                .codeDetailName("3145")
                .description("용인지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .regionGroup(savedGyonkiGroup)
                .build();
        Region suwon = Region.builder()
                .codeDetailName("3111")
                .description("수원지역 식별자")
                .orderSequence(1L)
                .useInfo(true)
                .regionGroup(savedGyonkiGroup)
                .build();
        savedGyonkiGroup.getRegions().add(yongin);
        savedGyonkiGroup.getRegions().add(suwon);
        Region savedyongin = userCodeRepository.save(yongin);
        Region savedSuwon = userCodeRepository.save(suwon);

        savedGyonkiGroup = groupCodeRepository.save(savedGyonkiGroup);
        Assertions.assertThat(savedGyonkiGroup.getRegions()).contains(savedyongin);
        Assertions.assertThat(savedGyonkiGroup.getRegions()).contains(savedSuwon);
    }
    @Test
    @Transactional
    void Kamis전국조회() {
        RegionGroup kamis = groupCodeRepository.findById("FDPREGN3100").get();
        log.info("결과 : {}",kamis.getRegions());
        Assertions.assertThat(kamis.getRegions().get(0).getRegionGroup()).isEqualTo(kamis);
    }
}