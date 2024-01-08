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
public class InnerProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "category_code",referencedColumnName="category_code"),
            @JoinColumn(name = "item_code",referencedColumnName="item_code"),
            @JoinColumn(name = "kind_code",referencedColumnName="kind_code"),
            @JoinColumn(name = "class_code",referencedColumnName="class_code"),
            @JoinColumn(name = "rank_code",referencedColumnName="rank_code")},
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
