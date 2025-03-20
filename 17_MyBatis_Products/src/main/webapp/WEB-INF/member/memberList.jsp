<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
memberList.jsp<br>

<script>
	function update(id){
		location.href="update.mb?id="+id;
	}
</script>

<h2>회원 리스트 화면(${lists.size() })</h2>

<form action="list.mb" method="post">
	<select name="whatColumn">
		<option value="">전체검색</option>
		<option value="id">id</option>
		<option value="gender">성별</option>
		<option value="hobby">취미</option>
		<option value="address1">주소1</option>
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색">
</form>

<table border="1">
	<tr>
		<td colspan="9">
			<input type="button" onClick="add()" value="추가하기">
		</td>
	</tr>
	<tr>
		<th>ID</th>
		<th>이름</th>
		<th>비번</th>
		<th>성별</th>
		<th>취미</th>
		<th>주소</th>
		<th>포인트</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	
	<c:forEach var="bean" items="${lists }">
		<tr>
			<td>${bean.id }</td>
			<td>${bean.name }</td>
			<td>${bean.password }</td>
			<td>${bean.gender }</td>
			<td>${bean.hobby }</td>
			<td>${bean.address1 } ${bean.address2 }</td>
			<td>${bean.mpoint }</td>
			<td><a href="delete.mb?id=${bean.id }">삭제</a></td>
			<td><input type="button" onClick="update(${bean.id })" value="수정"></td>
		</tr>
	</c:forEach>
</table>
${pageInfo.pagingHtml}