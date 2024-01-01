package com.example.fdppoc.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerSearchHistory {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumns(
        value = {
                @JoinColumn(referencedColumnName="categoryCode"),
                @JoinColumn(referencedColumnName="itemCode"),
                @JoinColumn(referencedColumnName="kindCode"),
                @JoinColumn(referencedColumnName="classCode"),
                @JoinColumn(referencedColumnName="rankCode")},
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private BaseProduct baseProduct;            //조회한상품
    @ManyToOne
    private UserCode regionCode;                //조회한지역
    @ManyToOne
    private MemberInfo targetMember;            //조회한고객
}
