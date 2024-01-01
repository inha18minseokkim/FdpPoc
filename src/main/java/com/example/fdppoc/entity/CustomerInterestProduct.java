package com.example.fdppoc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerInterestProduct {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(referencedColumnName="categoryCode"),
            @JoinColumn(referencedColumnName="itemCode"),
            @JoinColumn(referencedColumnName="kindCode"),
            @JoinColumn(referencedColumnName="classCode"),
            @JoinColumn(referencedColumnName="rankCode")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BaseProduct baseProduct;
    @ManyToOne
    private MemberInfo memberInfo;
}
