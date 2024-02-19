package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.RegionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupCodeRepository extends JpaRepository<RegionGroup,String> {
}
