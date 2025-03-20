<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
ProductDatail.jsp<br>

<h2>상품 상세 화면 ${pb.num}</h2>

<table border="1">
	<tr>
		<td rowspan="6">
			<img width="30px" alt="${pb.image}" src="<%=request.getContextPath() %>/resources/uploadImage/${pb.image}">
		</td>
		<th>상품명</th>
		<td>${pb.name}</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>${pb.price}</td>
	</tr>
	<tr>
		<th>재고 수량</th>
		<td>${pb.stock}</td>
	</tr>
	<tr>
		<th>설명</th>
		<td>${pb.contents}</td>
	</tr>
	<tr>
		<th>주문수량</th>
		<td>주문수량2 
			<form action="add.mall" method="post">
				<input type="hidden" name="num" value="${pb.num}">
				<input type="text" name="orderqty" value="1">
				<input type="submit" value="주문">
			</form>
		</td>
	</tr>
	<tr>
		<td colspan="2"><a href="list.prd?pageNumber=${param.pageNumber}&whatColumn=${param.whatColumn }&keyword=${param.keyword }">상품 리스트</a></td>
	</tr>
</table>