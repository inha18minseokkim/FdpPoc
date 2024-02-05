package com.example.fdppoc.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name="OriginalPriceUnique",columnNames = {"baseDate","regionInfoId","baseProductId","storeName"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OriginalPriceInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String baseDate;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserCode regionInfo;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BaseProduct baseProduct;
    private String storeName;
    private String itemName;
    private String kindName;
    private Long price;

}
