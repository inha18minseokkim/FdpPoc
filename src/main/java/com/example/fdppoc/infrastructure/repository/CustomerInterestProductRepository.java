package com.example.fdppoc.infrastructure.repository;

import com.example.fdppoc.domain.entity.CustomerInterestProduct;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerInterestProductRepository extends JpaRepository<CustomerInterestProduct,Long> {
    Optional<CustomerInterestProduct> getCustomerInterestProductByInnerProductAndMemberInfo(InnerProduct innerProduct, MemberInfo  memberInfo);
    List<CustomerInterestProduct> findAllByMemberInfoAndIsAvailable(MemberInfo memberInfo,Boolean isAvailable);
}
