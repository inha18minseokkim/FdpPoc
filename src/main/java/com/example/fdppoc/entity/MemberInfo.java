package com.example.fdppoc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name="MemberInfoUnique",columnNames = {"id","businessCode"}))
public class MemberInfo {
    @Id
    private Long id;
    private String businessCode;
    private Boolean isAgree;
}
