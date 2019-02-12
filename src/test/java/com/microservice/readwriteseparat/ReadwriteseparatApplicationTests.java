package com.microservice.readwriteseparat;

import com.microservice.readwriteseparat.po.TestPO;
import com.microservice.readwriteseparat.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadwriteseparatApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ReadwriteseparatApplicationTests.class);

    @Autowired
    private TestService aaaService;

    /**
     * 写库进行写入
     */
    @Test
    public void testWrite() {
        TestPO aaa = new TestPO();
        aaaService.insert(aaa);
    }

    /**
     * 读库（otadb1和otadb2随机）进行读取
     */
    @Test
    public void testRead() {
        TestPO aaa = aaaService.selectByPrimaryKey(1l);
    }

    /**
     * 写库进行写入
     */
    @Test
    public void testSave() {
        TestPO aaa = new TestPO();
        aaaService.save(aaa);
    }

    /**
     * 写库进行读取
     */
    @Test
    public void testReadFromMaster() {
        aaaService.getById(10001l);
    }

}

