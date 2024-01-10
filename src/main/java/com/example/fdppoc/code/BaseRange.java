package com.example.fdppoc.code;

import lombok.Getter;

@Getter
public enum BaseRange {
    Day(1),Week(7),Month(30),Half(182),Year(365);
    Integer gapDay;

    BaseRange(Integer gapDay) {
        this.gapDay = gapDay;
    }
}