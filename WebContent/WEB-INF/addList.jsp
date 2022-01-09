<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>

<%
	List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("gList");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook2/gbc" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="" required></td>
				<td>비밀번호</td>
				<td><input name="password" value="" required></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" style="width: 30rem" required></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add">
	</form>
	<br/>
	<%
	for(int i = 0; i < list.size(); i++) {
		int no = list.get(i).getNo();
	%>
		<table border="1">
			<tr>
				<td><%= no %></td>
				<td><%= list.get(i).getName() %></td>
				<td><%=  list.get(i).getRegDate()%></td>
				<td><a href="/guestbook2/gbc?action=deleteForm&no=<%= no%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4" style="width: 30rem"><%= list.get(i).getContent() %></td>
			</tr>
		</table>
		<br/>		
	<%
	}
	%>
</body>
</html>