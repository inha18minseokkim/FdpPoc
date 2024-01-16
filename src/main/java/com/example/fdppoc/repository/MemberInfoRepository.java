package com.example.fdppoc.repository;

import com.example.fdppoc.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo,Long> {
    Optional<MemberInfo> findMemberInfoByCustomerIdAndBusinessCode(String customerId,String businessCode);
}
