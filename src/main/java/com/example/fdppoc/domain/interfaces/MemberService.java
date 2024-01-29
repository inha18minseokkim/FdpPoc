package com.example.fdppoc.domain.interfaces;

import com.example.fdppoc.domain.dto.*;

import java.util.List;

public interface MemberService {
    GetMemberPushInfoResult getMemberPushInfo(GetMemberPushInfoCriteria in);
    void setMemberPushInfo(SetMemberPushInfoCriteria in);

    GetProductInterestResult getProductInterest(GetProductInterestCriteria in);
    void setProductInterest(SetProductInterestCriteria in);
    List<GetMemberInterestProductsResult> getMemberInterestProducts(GetMemberInterestProductsCriteria in);

    void insertProductHistory(InsertProductHistoryCriteria in);
    GetTopViewProductsResult getTopViewProducts(GetTopViewProductsCriteria in);

}
