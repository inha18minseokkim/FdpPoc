package com.example.fdppoc.domain.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLatestBaseDateResult {
    private String baseDate;
}
