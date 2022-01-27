<%@page import="java.sql.*"%>
<%@page import="com.koreait.db.DBconn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%	
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<jsp:setProperty property="*" name="member"/> 
	<% 
	
	if(dao.join(member) == 1){
		%>
		<script>
			alert('회원가입 완료되었습니다.');
			location.href='login.jsp';
		</script>
		<%
	}else{
		%>
			<script>
			alert('회원가입에 실패하였습니다.');
			history.back();
		</script>
		<%
		
	}

	%>
