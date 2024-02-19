package com.example.fdppoc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "regionGroup")
public class Region {
    @Id
    private String id;
    private String regionDetailName;
    private String description;
    private Long orderSequence;
    private Boolean useInfo;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RegionGroup regionGroup;
}
