<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int no = Integer.parseInt(request.getParameter("no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>삭제 폼입니다.</h1>
	
	<form action="/guestbook2/gbc" method="get">
		비밀번호<input type="text" name="passwordDelForm" value="" required>
		<button type="submit">확인</button>
		<input type="hidden" name="no" value="<%= no%>">
		<input type="hidden" name="action" value="delete">	
	</form>
	
	<a href="/guestbook2/gbc">메인으로 돌아가기</a>
</body>
</html>