package com.example.fdppoc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InnerCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String innerCategoryName;
    private Long orderSequence;
    private String additionalDescription;
    private Boolean isAvailable;
    @OneToMany(mappedBy = "innerCategory",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<InnerProduct> subProducts;

}
