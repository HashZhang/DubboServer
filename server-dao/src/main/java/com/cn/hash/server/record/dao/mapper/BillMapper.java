package com.cn.hash.server.record.dao.mapper;

import com.cn.hash.server.record.dao.domain.Bill;

public interface BillMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}