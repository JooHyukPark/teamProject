<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
mallList.jsp<br>

<h2>주문 내역</h2>

<table border="1">
	<tr>
		<td colspan="5">주문자 정보 : ${sessionScope.loginInfo.name }(${sessionScope.loginInfo.id })</td>
	</tr>
	<tr>
		<th>상품 번호</th>
		<th>상품명</th>
		<th>주문 수량</th>
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
	<tr>
		<td colspan="3"><a href="calculate.mall">결제하기</a>  <a href="list.prd">추가 주문</a></td>
		<td colspan="2">총 금액 : ${totalAmount }</td>
	</tr>	
</table>