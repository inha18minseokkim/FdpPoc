package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo,Long> {
    Optional<MemberInfo> findMemberInfoByCustomerIdAndBusinessCode(String customerId,String businessCode);
}
