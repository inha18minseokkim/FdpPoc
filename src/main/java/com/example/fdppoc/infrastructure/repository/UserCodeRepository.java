package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCode,Long> {
}
