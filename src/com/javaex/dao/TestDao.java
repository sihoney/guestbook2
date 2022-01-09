package com.javaex.dao;

import java.util.List;
import com.javaex.vo.GuestbookVo;

public class TestDao {
	public static void main(String[] args) {
		
		GuestbookDao guestbookDao = new GuestbookDao();
		
		List<GuestbookVo> list = guestbookDao.getList();
		
		for(GuestbookVo gvo : list) {
			System.out.println(gvo.toString());
		}
		
	}
}
