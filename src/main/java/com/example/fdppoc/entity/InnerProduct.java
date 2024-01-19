package com.example.fdppoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            @JoinColumn(name = "categoryCode",referencedColumnName="categoryCode"),
            @JoinColumn(name = "itemCode",referencedColumnName="itemCode"),
            @JoinColumn(name = "kindCode",referencedColumnName="kindCode"),
            @JoinColumn(name = "classCode",referencedColumnName="classCode"),
            @JoinColumn(name = "gradeCode",referencedColumnName="gradeCode")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    ) //joinColumn 쓰는 이유 : baseProduct는 실제 운영환경에서 안쓸거임. + 원본 코드 보존이 필요함(이력도 마찬가지)
    private BaseProduct baseProduct;

    private Boolean isMainMaterial;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumns(
            value = @JoinColumn(name="innerCategoryId",referencedColumnName = "id"),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private InnerCategory innerCategory;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
}
