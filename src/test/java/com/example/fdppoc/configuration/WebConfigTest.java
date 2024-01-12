package com.example.fdppoc.configuration;

import com.example.fdppoc.code.BaseRange;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WebConfigTest {
    @Test
    void enum변환테스트() {
        BaseRange week = BaseRange.valueOf("WEEK");
        Assertions.assertThat(week).isEqualTo(BaseRange.WEEK);
    }

}