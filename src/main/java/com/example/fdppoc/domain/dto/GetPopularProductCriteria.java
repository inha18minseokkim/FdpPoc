package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class GetPopularProductCriteria {
    private String baseDate;
    private LocalDateTime currentTime;
    private Long rangeHour;
    private String regionGroupId;
}
