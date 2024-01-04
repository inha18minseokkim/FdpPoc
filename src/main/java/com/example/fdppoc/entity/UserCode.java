package com.example.fdppoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UserCode {
    @Id
    @GeneratedValue
    private Long id;
    private String codeDetailName;
    private String description;
    private Long orderSequence;
    private Boolean useInfo;
    @ManyToOne
    private UserGroupCode userGroupCode;
}