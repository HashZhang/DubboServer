package com.cn.hash.server.record.test;

import com.cn.hash.server.record.dao.domain.BillKind;
import com.cn.hash.server.record.dao.mapper.ops_evaluate_stat_resultMapper;
import com.cn.hash.server.record.dao.domain.ops_evaluate_stat_result;
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
@ContextConfiguration(locations = {"/test-dao.xml"})
@TestExecutionListeners(value = {TransactionalTestExecutionListener.class})
public class BaseTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    ops_evaluate_stat_resultMapper ops_evaluate_stat_resultMapper;

    @Test
    public void testBillKind() {
        ops_evaluate_stat_result ops_evaluate_stat_result = new ops_evaluate_stat_result();
        ops_evaluate_stat_result.setId(19);
        ops_evaluate_stat_resultMapper.insertSelective(ops_evaluate_stat_result);
    }
}
