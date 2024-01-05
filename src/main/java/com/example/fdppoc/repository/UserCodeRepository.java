package com.example.fdppoc.repository;

import com.example.fdppoc.entity.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCode,Long> {
}
