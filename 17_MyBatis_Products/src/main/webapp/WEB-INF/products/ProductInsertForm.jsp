<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<style>
	.err{
		color: red;
	}
</style>

ProductInsertForm.jsp<br>
<h2>상품 추가 화면</h2>

<form:form commandName="pbean" action="insert.prd" method="post" enctype="multipart/form-data">
	*상품명 <input type="text" name="name" value="${pbean.name}">
	<form:errors cssClass="err" path="name"/>
	<br><br>
	제조회사 <input type="text" name="company" value="${pbean.company}">
	<br><br>
	*가격 <input type="text" name="price" value="${pbean.price}">
	<form:errors cssClass="err" path="price"/>
	<br><br>
	재고 수량 <input type="text" name="stock" value="${pbean.stock}">
	<br><br>
	적립 포인트 <input type="text" name="point" value="${pbean.point}">
	<br><br>
	*설명 <input type="text" name="contents" value="${pbean.contents}">
	<form:errors cssClass="err" path="contents"/>
	<br><br>
	*상품 이미지 <input type="file" name="upload">
	<form:errors cssClass="err" path="upload"/>
	<br><br>
	입고 일자 <input type="date" name="inputdate" value="${pbean.inputdate}">
	<br><br>
	<input type="submit" value="추가하기">
</form:form>