<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<style>
	.err{
		color: red;
	}
</style>


<script>
document.getElementById('fileInput').addEventListener('change', function(event) {
    let fileInput = event.target;
    let fileNameDisplay = document.getElementById('fileName');
    let existingUpload = document.getElementById('existingUpload');

    if (fileInput.files.length > 0) {
        // 새 파일이 선택되었을 경우
        fileNameDisplay.textContent = fileInput.files[0].name;
    } else {
        // 새 파일이 선택되지 않았을 경우 기존 파일 유지
        fileNameDisplay.textContent = existingUpload.value || "선택된 파일 없음";
    }
});
</script>


ProductUpdateForm.jsp<br>
<h2>상품 수정 화면</h2>

"num" value=${pbean.num}<br>
"pageNumber" value=${param.pageNumber}<br>
"keyword" value=${param.keyword}<br>
"whatColumn" value=${param.whatColumn}<br>

<form:form commandName="pbean" action="update.prd" method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${pbean.num}">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="whatColumn" value="${param.whatColumn}">


	<table>
		<tr>
			<td>*상품명</td>
			<td>
			<input type="text" name="name" value="${pbean.name}">
			<form:errors cssClass="err" path="name"/>
			</td>
		</tr>
		<tr>
			<td>제조회사</td>
			<td><input type="text" name="company" value="${pbean.company}"></td>
		</tr>
		<tr>
			<td>*가격</td>
			<td>
			<input type="text" name="price" value="${pbean.price}">
			<form:errors cssClass="err" path="price"/>
			</td>
		</tr>
		<tr>
			<td>재고 수량</td>
			<td><input type="text" name="stock" value="${pbean.stock}"></td>
		</tr>
		<tr>
			<td>적립 포인트</td>
			<td><input type="text" name="point" value="${pbean.point}"></td>
		</tr>
		<tr>
			<td>*설명</td>
			<td>
			<input type="text" name="contents" value="${pbean.contents}">
			<form:errors cssClass="err" path="contents"/>
			</td>
		</tr>
		<tr>
			<td>*상품 이미지</td>
			<td>
			<img width="30px" alt="${pbean.image}" src="<%=request.getContextPath() %>/resources/uploadImage/${pbean.image}">
			<form:errors cssClass="err" path="image"/>
			<input type="file" name="upload" id="fileInput"><br>
			<input type="text" name="upload2" value="${pbean.image}">
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="수정하기"></td>
		</tr>
	</table>
</form:form>