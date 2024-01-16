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
            @JoinColumn(name = "categoryCode",referencedColumnName="categoryCode"),
            @JoinColumn(name = "itemCode",referencedColumnName="itemCode"),
            @JoinColumn(name = "kindCode",referencedColumnName="kindCode"),
            @JoinColumn(name = "classCode",referencedColumnName="classCode"),
            @JoinColumn(name = "gradeCode",referencedColumnName="gradeCode")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BaseProduct baseProduct;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberInfo memberInfo;
    private Boolean isAvailable;
}

