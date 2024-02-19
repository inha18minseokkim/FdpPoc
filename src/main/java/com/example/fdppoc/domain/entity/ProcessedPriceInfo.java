package com.example.fdppoc.domain.entity;

import com.example.fdppoc.code.BaseRange;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name="ProcessedPriceUnique",columnNames = {"baseDate","regionId","baseProductId","baseRange"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedPriceInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String baseDate;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Region region;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BaseProduct baseProduct;
    @Enumerated(EnumType.STRING)
    private BaseRange baseRange;
    private Long price;
}

