package com.example.fdppoc.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerSearchHistory {
    @Id
    @GeneratedValue
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
    @JoinColumn(referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserCode regionCode;                //조회한지역
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberInfo memberInfo;            //조회한고객
    //이력성 테이블은 굳이 외래키 제약을 둘필요없고, 후에 데이터 분석을 위해 조인을 최소화하게 만들어보자
}
