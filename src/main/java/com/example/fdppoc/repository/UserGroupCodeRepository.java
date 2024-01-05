package com.example.fdppoc.repository;

import com.example.fdppoc.entity.UserGroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupCodeRepository extends JpaRepository<UserGroupCode,Long> {
}
