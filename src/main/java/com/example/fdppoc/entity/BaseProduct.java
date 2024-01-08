package com.example.fdppoc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.javassist.runtime.Inner;

import java.util.List;

@Entity
@Data
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(name="productUnique",columnNames = {"categoryCode","itemCode","kindCode","classCode","rankCode"})})
public class BaseProduct {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "category_code",length = 3)
    private String categoryCode;    //pd_ctgr_cd
    @Column(name="item_code",length= 4)
    private String itemCode;            //pd_lsar_cd
    @Column(name="kind_code",length= 3)
    private String kindCode;            //pd_knd_cd
    @Column(name="class_code",length=2)
    private String classCode;           //whls_rtl_dcd
    @Column(name="rank_code",length=2)
    private String rankCode;            //pd_grade_cd
    private String unitName;            //snog_unit_nm
    private Float unitValue;            //snog_unit_val
    private Boolean isAvailable;            //사용여부
    private String categoryName;
    private String itemName;
    private String kindName;
    private String gradeName;
    @OneToMany(mappedBy = "b    aseProduct",fetch = FetchType.LAZY)
    private List<InnerProduct> innerProducts;
}
