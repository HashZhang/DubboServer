package com.cn.hash.server.manager.record.util;

import com.cn.hash.server.record.dao.domain.BillKind;
import com.cn.hash.server.record.dao.domain.Bill;

/**
 * Created by Hash ZHANG on 2016/4/3.
 */
public class Transformer {
    public static Bill toServerBill(com.cn.hash.server.client.record.domain.Bill bill) {
        Bill bill1 = new Bill();
        bill1.setUserId(bill.getUserId());
        bill1.setKind(bill.getKind());
        bill1.setId(bill.getId());
        bill1.setAmount(bill.getAmount());
        bill1.setCreatedTime(bill.getCreatedTime());
        bill1.setUpdatedTime(bill.getUpdatedTime());
        return bill1;
    }

    public static com.cn.hash.server.client.record.domain.Bill toClientBill(Bill bill) {
        com.cn.hash.server.client.record.domain.Bill bill1 = new com.cn.hash.server.client.record.domain.Bill();
        bill1.setUserId(bill.getUserId());
        bill1.setKind(bill.getKind());
        bill1.setId(bill.getId());
        bill1.setAmount(bill.getAmount());
        bill1.setCreatedTime(bill.getCreatedTime());
        bill1.setUpdatedTime(bill.getUpdatedTime());
        return bill1;
    }

    public static BillKind toServerBillKind(com.cn.hash.server.client.record.domain.BillKind billKind){
        BillKind billKind1 = new BillKind();
        billKind1.setId(billKind.getId());
        billKind1.setKind(billKind.getKind());
        billKind1.setUserId(billKind.getUserId());
        return billKind1;
    }
    public static com.cn.hash.server.client.record.domain.BillKind toClientBillKind(BillKind billKind){
        com.cn.hash.server.client.record.domain.BillKind billKind1 = new com.cn.hash.server.client.record.domain.BillKind();
        billKind1.setId(billKind.getId());
        billKind1.setKind(billKind.getKind());
        billKind1.setUserId(billKind.getUserId());
        return billKind1;
    }
}
