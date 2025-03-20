<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
start.jsp<br>

<%
	String viewProduct = request.getContextPath() + "/list.prd";
	String viewMember = request.getContextPath() + "/list.mb";
	String viewOrder = request.getContextPath() + "/order.mall";	
%>

<a href="<%= viewProduct %>">상품 목록 보기</a><br><br>
<a href="<%= viewMember %>">회원 목록 보기</a><br><br>
<a href="<%= viewOrder %>">나의 주문 내역 보기</a><br><br>


<hr>
<a href="loginForm.mb">로그인</a><br><br>