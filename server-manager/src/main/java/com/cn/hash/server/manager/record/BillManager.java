package com.cn.hash.server.manager.record;

import com.cn.hash.server.client.record.domain.BillKind;
import com.cn.hash.server.manager.record.util.Transformer;
import com.cn.hash.server.record.dao.mapper.BillKindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHON-989 on 2016/4/2.
 */
@Component
public class BillManager {
    @Autowired
    BillKindMapper billKindMapper;

    public List<BillKind> returnAllBillKinds(){
        List<com.cn.hash.server.record.dao.domain.BillKind> billKinds = billKindMapper.selectAll();
        List<BillKind> billKinds1 = new ArrayList<BillKind>();
        for(com.cn.hash.server.record.dao.domain.BillKind billKind:billKinds){
            billKinds1.add(Transformer.toClientBillKind(billKind));
        }
        return billKinds1;
    }
}
