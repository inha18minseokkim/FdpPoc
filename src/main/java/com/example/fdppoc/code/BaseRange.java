package com.example.fdppoc.code;

import lombok.Getter;

@Getter
public enum BaseRange {
    DAY(1), WEEK(7), MONTH(30), HALF(182), YEAR(365);
    Integer gapDay;

    BaseRange(Integer gapDay) {
        this.gapDay = gapDay;
    }
}