<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="com.koreait.db.DBconn"%>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/> 
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/> 
<jsp:setProperty property="*" name="member"/> 

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
	// 로그인 하지 않았을 경우 login.jsp 로 이동
	if(session.getAttribute("userid")== null){
%>
		<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp'
		</script>
<%
	}else{
		request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community - List</title>
</head>
<body>
<h2>커뮤니티 - 리스트</h2>
<p>게시글: ${dao.totalCount()} 개</p>
<table border="1" width="800">
<tr>
	<th width="50">번호</th>
	<th width="300">제목</th>
	<th width="100">글쓴이</th>
	<th width="75">조회수</th>
	<th width="200">날짜</th>
	<th width="75">좋아요</th>
</tr>	
<tr>
	
	<c:set var="arr" value="${dao.list()}"/>
	
	<c:forEach var="list" items="${arr}" varStatus="i">
         <td>${list}</td>
	</c:forEach>
	
</tr>



<tr>


	<td colspan="6" style="text-align:center">1 2 3 </td>
</tr>
</table>
<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'">
<input type="button" value="메인으로" onclick="location.href='../login.jsp'"></p>
</body>
</html>
		<% 
		}
		%>


