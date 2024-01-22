package com.example.fdppoc.repository;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.CustomerInterestProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInterestProductRepository extends JpaRepository<CustomerInterestProduct,Long> {
    Optional<CustomerInterestProduct> getCustomerInterestProductByInnerProductAndMemberInfo(InnerProduct innerProduct, MemberInfo  memberInfo);
}
