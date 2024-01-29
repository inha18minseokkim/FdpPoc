package com.example.fdppoc.domain.entity;

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
@Table(uniqueConstraints = @UniqueConstraint(name="CustomerInterestUnique",columnNames = {"customerId","categoryCode","itemCode","kindCode","classCode","gradeCode"}))
public class CustomerInterestProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "innerProductId",referencedColumnName="id")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private InnerProduct innerProduct;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "customerId", referencedColumnName = "customerId"),
            @JoinColumn(name = "businessCode", referencedColumnName = "businessCode")
    },foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberInfo memberInfo;
    private Boolean isAvailable;
}

