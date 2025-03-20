<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
myShoppingDetail<br>

<%@ include file="../common/common.jsp" %>

<h2>주문 상세 내역</h2>

<table>
	<tr>
		<td colspan="2">고객명 : </td>
		<td colspan="3">송장 번호(주문번호) : ${oid }</td>
	</tr>
	<tr>
		<td colspan="5">배송지: </td>
	</tr>
	<tr>
		<th>순번</th>
		<th>상품명(상품번호)</th>
		<th>수량</th>
		<th>단가</th>
		<th>금액</th>
	</tr>
	<c:forEach var="bean" items="${shopLists}">
		<tr>
			<td>${bean.pnum }</td>
			<td>${bean.pname }</td>
			<td>${bean.qty }</td>
			<td>${bean.price }</td>
			<td>${bean.amount }</td>
		</tr>
	</c:forEach>
</table>