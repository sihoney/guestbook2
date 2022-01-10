package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("controller");
		//request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("add".equals(action)) {
						
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestbookVo gvo = new GuestbookVo(name, password, content);
			guestbookDao.addGuest(gvo);
			
			// redirect
			WebUtil.redirect(request, response, "/guestbook2/gbc");
			
		}
		else if("deleteForm".equals(action)) {
			
			// forward
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
		}
		else if("delete".equals(action)) {

			int no = Integer.parseInt(request.getParameter("no"));
			String passwordDelForm = request.getParameter("passwordDelForm");
			
			/*
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.deleteGuest(vo);
			 */
			
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestbookVo gvo = guestbookDao.getGuest(no);
			
			if(passwordDelForm.equals(gvo.getPassword())) {
				System.out.println("비밀번호가 일치합니다.");
				guestbookDao.deleteGuest(no);
			} 
			else {
				System.out.println("비밀번호가 다릅니다.");
			}
			
			// redirect
			response.sendRedirect("/guestbook2/gbc");
			
		}
		else { // 리스트를 기본 값으로
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> list = guestbookDao.getList();
			
			request.setAttribute("gList", list);
			
			// forward
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
