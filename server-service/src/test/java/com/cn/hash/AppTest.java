package com.cn.hash;

import com.cn.hash.server.client.record.domain.BillKind;
import com.cn.hash.server.record.service.BillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations = {"/test-service.xml"})
public class AppTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    BillService billService;
    @Test
    public void testBillService(){
        List<BillKind> billKinds = billService.getAllBillKinds();
        for(BillKind billKind:billKinds){
            System.out.println(billKind.toString());
        }
    }
}
