<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
memberRegisterForm.jsp<br>
<%@ include file="../common/common.jsp" %>

<style>
	.err{
		color: red;
	}
</style>

<%
	String[] hobby = {"마라톤", "영화감상", "게임", "공부"};
	pageContext.setAttribute("hobby", hobby);
%>

<h2>회원 가입 화면</h2>
<form:form commandName="mbean" action="register.mb" method="post">
	아이디 <input type="text" name="id" value="${mbean.id }">
	<form:errors cssClass="err" path="id"/>
	<br><br>
	이름 <input type="text" name="name" value="${mbean.name }">
	<form:errors cssClass="err" path="name"/>
	<br><br>
	비번 <input type="text" name="password" value="${mbean.password }">
	<form:errors cssClass="err" path="password"/>
	<br><br>
	
	성별 
		<input type="radio" name="gender" value="여자"
			<c:if test="${mbean.gender eq '여자' }">checked</c:if>
		>여자
		<input type="radio" name="gender" value="남자"
			<c:if test="${mbean.gender eq '남자' }">checked</c:if>
		>남자
		<form:errors cssClass="err" path="gender"/>
		<br><br>
	
	취미 
		<c:forEach var="h" items="${hobby }">
			<input type="checkbox" name="hobby" value="${h }"
				<c:if test="${fn:contains(mbean.hobby,h) }">checked</c:if>
			>
		</c:forEach>
		<form:errors cssClass="err" path="hobby"/>
		<br><br>
	
	주소1 <input type="text" name="address1" value="${mbean.address1 }">
	<form:errors cssClass="err" path="address1"/>
	<br><br>
	주소2 <input type="text" name="address2" value="${mbean.address2 }">
	<br><br>
	적립포인트 <input type="text" name="mpoint" placeholder="0" value="${mbean.mpoint }">
	<br><br>
	<input type="submit" value="추가하기">
</form:form>
