package com.example.fdppoc.entity;

import com.example.fdppoc.code.BaseRange;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProcessedPriceInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String baseDate;
    @ManyToOne
    private UserCode region;
    @ManyToOne
    private BaseProduct baseProduct;
    private BaseRange baseRange;
    private Long price;
}
