package com.example.fdppoc.domain.entity;

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
    private String id;

    @OneToMany(mappedBy = "innerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BaseProduct> baseProducts;

    private Boolean isMainMaterial;
    private Boolean isAvailable;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="innerCategoryId",referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private InnerCategory innerCategory;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
}
