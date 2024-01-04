package com.example.fdppoc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InnerProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(referencedColumnName="categoryCode"),
            @JoinColumn(referencedColumnName="itemCode"),
            @JoinColumn(referencedColumnName="kindCode"),
            @JoinColumn(referencedColumnName="classCode"),
            @JoinColumn(referencedColumnName="rankCode")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    ) //joinColumn 쓰는 이유 : baseProduct는 실제 운영환경에서 안쓸거임. + 원본 코드 보존이 필요함(이력도 마찬가지)
    private BaseProduct baseProduct;

    private Boolean isMainMaterial;
    private String classificationCode;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
}
