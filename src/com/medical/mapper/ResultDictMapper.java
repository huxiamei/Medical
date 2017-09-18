package com.medical.mapper;

import java.util.List;

import com.medical.model.ResultDict;

public interface ResultDictMapper {
    List<ResultDict> getResultDict();
    
	int insert(ResultDict record);

    int insertSelective(ResultDict record);
}