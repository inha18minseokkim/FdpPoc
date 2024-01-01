package com.example.fdppoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MemberInfo {
    @Id
    private Long id;
    private String businessCode;
    private Boolean isAgree;
}
