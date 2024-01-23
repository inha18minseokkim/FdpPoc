package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class GetPopularProductCriteria {
    private LocalDateTime currentTime;
    private Long rangeHour;
}
