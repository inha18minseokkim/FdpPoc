package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class GetTopViewedInnerProductInDto {
    private LocalDateTime currentTime;
    private Integer rangeHour;
}
