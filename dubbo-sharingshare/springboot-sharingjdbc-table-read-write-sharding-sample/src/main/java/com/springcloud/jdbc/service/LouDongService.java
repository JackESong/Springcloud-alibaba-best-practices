package com.springcloud.jdbc.service;

import com.springcloud.jdbc.po.LouDong;

import java.util.List;

public interface LouDongService {

	List<LouDong> list();
	
	Long addLouDong(LouDong louDong);
		
}
