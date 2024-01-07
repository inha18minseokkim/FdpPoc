package com.example.fdppoc.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class InnerProductRepositoryCustomTest {
    @Autowired
    InnerProductRepositoryCustom innerProductRepositoryCustom;

    @Test
    void 동적쿼리_조회테스트(){

    }
}