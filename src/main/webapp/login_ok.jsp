
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<jsp:setProperty property="*" name="member"/>

 
<%     

	      if(dao.login(member)!=null){
	  	    session.setAttribute("userid", member.getUserid());
		    session.setAttribute("idx", member.getIdx());
		    session.setAttribute("name", member.getName());
	    	  %>
	    	<script>
    	  	alert("로그인 되었습니다.");
    	  	location.href="login.jsp";
    	  </script>
  
	    	<%
	      }else{
	      	%>

    	  <script>
    	   alert("아이디 및 비밀번호를 확인하세요");
    	   history.back();
    	  </script>
    	  	<%
    	  	} 
    	  	%>
