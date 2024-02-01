package com.example.fdppoc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userGroupCode")
public class UserCode {
    @Id
    private String id;
    private String codeDetailName;
    private String description;
    private Long orderSequence;
    private Boolean useInfo;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserGroupCode userGroupCode;
}
