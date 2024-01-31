package com.example.fdppoc.code;

import lombok.Getter;

import java.util.List;

@Getter
public enum BaseRange {
    DAY(1), WEEK(7), MONTH(30), HALF(182), YEAR(365);
    Integer gapDay;

    public static List<BaseRange> getDetailRangeList() {
        return List.of(WEEK,MONTH,HALF,YEAR);
    }

    BaseRange(Integer gapDay) {
        this.gapDay = gapDay;
    }
}