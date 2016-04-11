package com.cn.hash.server.record.service.impl;

import com.cn.hash.server.client.record.domain.BillKind;
import com.cn.hash.server.manager.record.BillManager;
import com.cn.hash.server.record.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JHON-989 on 2016/4/3.
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillManager billManager;

    public List<BillKind> getAllBillKinds() {
        return billManager.returnAllBillKinds();
    }
}
