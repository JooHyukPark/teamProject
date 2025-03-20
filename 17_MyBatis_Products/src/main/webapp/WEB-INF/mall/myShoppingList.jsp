<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
myShoppingList<br>
<%@ include file="../common/common.jsp" %>

<h2>주문 내역</h2>

<table>
	<tr>
		<td colspan="3">
			주문자 정보 : ${sessionScope.loginInfo.name }( ${sessionScope.loginInfo.id })
		</td>
	</tr>
	<tr>
		<th>주문 번호</th>
		<th>주문 일자</th>
		<th>상세 보기</th>
	</tr>
	<c:forEach var="bean" items="${olist }"></c:forEach>
	<tr>
		<td>${bean.oid }</td>
		<td>${bean.orderdate }</td>
		<td><a href="orderDetailView.mall?oid=${bean.oid }">상세보기</a></td>
	</tr>
</table>