package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterInDto;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.interfaces.BaseProductReader;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BaseProductReaderMyBatisImpl extends BaseProductReader {
    @Override
    List<FindBaseProductWithFilterOutDto> findBaseProductWithFilter(FindBaseProductWithFilterInDto in);
}
