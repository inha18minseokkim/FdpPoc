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
    )
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
