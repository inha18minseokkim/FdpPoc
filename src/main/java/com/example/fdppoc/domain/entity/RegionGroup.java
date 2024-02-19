package com.example.fdppoc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class RegionGroup {
    @Id
    private String id;
    private String groupDetailName;
    private String description;
    private Long orderSequence;
    private Boolean useInfo;
    @OneToMany
    private List<Region> regions;
}
