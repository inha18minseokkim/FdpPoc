package com.example.fdppoc.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchHistory {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "innerProductId",referencedColumnName="id")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private InnerProduct innerProduct;            //조회한상품
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserGroupCode regionGroup;                //조회한지역
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberInfo memberInfo;            //조회한고객
    //이력성 테이블은 굳이 외래키 제약을 둘필요없고, 후에 데이터 분석을 위해 조인을 최소화하게 만들어보자
    private LocalDateTime submitTime;
}
