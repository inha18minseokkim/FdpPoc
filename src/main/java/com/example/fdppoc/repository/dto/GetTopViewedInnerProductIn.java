package com.example.fdppoc.repository.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class GetTopViewedInnerProductIn {
    private LocalDateTime currentTime;
    private Integer rangeHour;
}
