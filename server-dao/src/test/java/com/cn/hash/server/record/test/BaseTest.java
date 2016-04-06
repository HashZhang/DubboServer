package com.cn.hash.server.record.test;

import com.cn.hash.server.record.dao.domain.BillKind;
import com.cn.hash.server.record.dao.mapper.BillKindMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.UUID;

/**
 * Created by Hash ZHANG on 2016/4/2.
 */
@ContextConfiguration(locations = { "/test-dao.xml" })
@TestExecutionListeners(value={TransactionalTestExecutionListener.class})
public class BaseTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    BillKindMapper billKindMapper;

    @Test
    public void testBillKind(){
        BillKind billKind = new BillKind();
        billKind.setId(String.valueOf(UUID.randomUUID()));
        billKind.setKind("test");
        billKind.setUserId("test");
        billKindMapper.insertSelective(billKind);
    }
}
