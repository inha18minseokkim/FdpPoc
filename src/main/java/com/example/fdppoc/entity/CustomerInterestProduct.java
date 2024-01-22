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
    @JoinColumn(name = "customerId", referencedColumnName = "customerId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberInfo memberInfo;
    private Boolean isAvailable;
}

