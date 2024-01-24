package com.example.fdppoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InnerProduct {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "innerProduct",fetch = FetchType.LAZY)
    private List<BaseProduct> baseProducts;

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
