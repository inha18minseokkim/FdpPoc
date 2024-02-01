package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.UserGroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupCodeRepository extends JpaRepository<UserGroupCode,String> {
}
