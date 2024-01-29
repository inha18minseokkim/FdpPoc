package com.example.fdppoc.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name="OriginalPriceUnique",columnNames = {"baseDate","regionInfoId","baseProductId","storeName"})})
public class OriginalPriceInfo {
    @Id
    @GeneratedValue
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
