package com.example.fdppoc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name="MemberInfoUnique",columnNames = {"customerId","businessCode"}))
public class MemberInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 15)
    private String customerId;
    private String businessCode;
    private Boolean isAgree;
}
