package com.example.fdppoc.repository;

import com.example.fdppoc.entity.UserCode;
import com.example.fdppoc.entity.UserGroupCode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCodeRepositoryTest {
    @Autowired
    UserCodeRepository userCodeRepository;
    @Autowired
    UserGroupCodeRepository groupCodeRepository;

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void usercode_insertionTest() {
////        UserGroupCode kamisApiRegion = UserGroupCode.builder()
////                .codeDetailName("KamisApiRegionCode")
////                .description("Kamis Api용 지역코드")
////                .orderSequence(1L)
////                .userCodes(new ArrayList<>())
////                .useInfo(true)
////                .build();
//        UserGroupCode kamisApiRegion = groupCodeRepository.findById(1L).get();
////        UserCode seoul = UserCode.builder()
////                .codeDetailName("1101")
////                .description("서울지역 식별자")
////                .orderSequence(1L)
////                .useInfo(true)
////                .userGroupCode(kamisApiRegion)
////                .build();
////        UserCode daegu = UserCode.builder()
////                .codeDetailName("2200")
////                .description("대구지역 식별자")
////                .orderSequence(1L)
////                .useInfo(true)
////                .userGroupCode(kamisApiRegion)
////                .build();
////        UserCode busan = UserCode.builder()
////                .codeDetailName("2100")
////                .description("부산지역 식별자")
////                .orderSequence(1L)
////                .useInfo(true)
////                .userGroupCode(kamisApiRegion)
////                .build();
//        UserCode suwon = UserCode.builder()
//                .codeDetailName("3111")
//                .description("수원지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
//        UserCode yongin = UserCode.builder()
//                .codeDetailName("3145")
//                .description("용인지역 식별자")
//                .orderSequence(1L)
//                .useInfo(true)
//                .userGroupCode(kamisApiRegion)
//                .build();
//        userCodeRepository.save(suwon);
//        userCodeRepository.save(yongin);
//        kamisApiRegion.getUserCodes().add(yongin);
//        kamisApiRegion.getUserCodes().add(suwon);
////        kamisApiRegion.getUserCodes().add(busan);
////        userCodeRepository.save(seoul);
////        userCodeRepository.save(daegu);
////        userCodeRepository.save(busan);
//        groupCodeRepository.save(kamisApiRegion);
//    }

}