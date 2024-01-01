package com.example.fdppoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OriginalPriceInfo {
    @Id
    private Long id;
    private String baseDate;
    @ManyToOne
    private UserCode regionInfo;
    @ManyToOne
    private BaseProduct baseProduct;
    private String storeName;
    private String itemName;
    private String kindName;
    private Long price;

}
