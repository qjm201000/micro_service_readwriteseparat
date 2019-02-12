package com.microservice.readwriteseparat.service;

import com.microservice.readwriteseparat.annotation.Master;
import com.microservice.readwriteseparat.mapper.TestPOMapper;
import com.microservice.readwriteseparat.po.TestPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService{

    @Autowired
    private TestPOMapper testPOMapper;

    public int insert(TestPO aaa) {
        return testPOMapper.insert(aaa);
    }

    @Master
    public int save(TestPO aaa) {
        return testPOMapper.insert(aaa);
    }

    public TestPO selectByPrimaryKey(Long id) {
        return testPOMapper.selectByPrimaryKey(id);
    }

    @Master
    public TestPO getById(Long id) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return testPOMapper.selectByPrimaryKey(id);
    }
}
