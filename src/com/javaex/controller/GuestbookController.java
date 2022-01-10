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
import com.javaex.vo.GuestbookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("controller");
		String action = request.getParameter("action");
		
		if("addList".equals(action)) {
			System.out.println(action);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> list = guestbookDao.getList();
			
			request.setAttribute("gList", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
		}
		else if("add".equals(action)) {
			System.out.println(action);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo gvo = new GuestbookVo(name, password, content);
			
			guestbookDao.addGuest(gvo);
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		else if("deleteForm".equals(action)) {
			System.out.println(action);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
		}
		else if("delete".equals(action)) {
			System.out.println(action);
			
			int no = Integer.parseInt(request.getParameter("no"));
			String passwordDelForm = request.getParameter("passwordDelForm");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			GuestbookVo gvo = guestbookDao.getGuest(no);
			
			if(passwordDelForm.equals(gvo.getPassword())) {
				System.out.println("비밀번호가 일치합니다.");
				
				guestbookDao.deleteGuest(no);
			} 
			else {
				System.out.println("비밀번호가 다릅니다.");
			}
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		else {
			System.out.println("파라미터 값 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
