package com.springcloud.jdbc.repository;

import java.util.List;

import com.springcloud.jdbc.po.LouDong;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LouDongRepository {
	
	Long addLouDong(LouDong louDong);
	
	List<LouDong> list();
}
