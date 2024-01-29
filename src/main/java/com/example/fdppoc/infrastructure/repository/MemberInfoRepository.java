package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.infrastructure.interfaces.MemberInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo,Long> , MemberInfoRepositoryCustom {
    Optional<MemberInfo> findMemberInfoByCustomerIdAndBusinessCode(String customerId,String businessCode);
}
