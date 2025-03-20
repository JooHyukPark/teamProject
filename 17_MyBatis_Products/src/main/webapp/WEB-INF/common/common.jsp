<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
common.jsp<br>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

접속자 아이디 : ${sessionScope.loginInfo.id }<br>
접속자 아이디 : ${loginInfo.id }<br>

<a href="<%= request.getContextPath() %>/start.jsp">시작페이지</a>
<a href="<%= request.getContextPath() %>/logout.jsp">로그아웃</a>