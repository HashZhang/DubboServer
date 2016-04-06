package com.cn.hash.server.record.dao.mapper;

import com.cn.hash.server.record.dao.domain.BillKind;

import java.util.List;

public interface BillKindMapper {
    int deleteByPrimaryKey(String id);

    int insert(BillKind record);

    int insertSelective(BillKind record);

    List<BillKind> selectAll();

    BillKind selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BillKind record);

    int updateByPrimaryKey(BillKind record);
}