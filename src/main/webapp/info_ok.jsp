<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%	
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/> 
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/> 
<jsp:setProperty property="*" name="member"/> 
<%
	// 로그인 하지 않았을 경우 login.jsp 로 이동
	if(session.getAttribute("userid")== null){
%>
		<script>
		alert('로그인 후 이용하세요');
		location.href='./login.jsp'
		</script>
<%
	}else{
	// 데이터를 받음
	
		request.setCharacterEncoding("UTF-8");
		member.setIdx((int)session.getAttribute("idx"));
		dao.edit(member);
	%>
	<script>
	alert("회원정보가 수정되었습니다.");
	location.href="./info.jsp";
	</script>
	<%
	
	}
	%>