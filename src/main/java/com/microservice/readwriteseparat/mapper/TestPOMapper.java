package com.microservice.readwriteseparat.mapper;

import com.microservice.readwriteseparat.po.TestPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestPO record);

    int insertSelective(TestPO record);

    TestPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestPO record);

    int updateByPrimaryKey(TestPO record);
}