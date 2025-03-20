<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

web-inf\member\memberLoginForm.jsp<br>

<script>
	function register(){
		location.href="register.mb";
	}
	
	function memberList(){
		location.href="list.mb";;
	}
</script>

${num }
${pageNumber }
${whatColumn }
${keyword }
<form action="loginForm.mb" method="post">
	<input type="hidden" name="num" value="${num }">
	<input type="hidden" name="pageNumber" value="${pageNumber }">
	<input type="hidden" name="whatColumn" value="${whatColumn }">
	<input type="hidden" name="keyword" value="${keyword }">
<table border="1">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td>비번</td>
		<td><input type="text" name="password"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="로그인">
			<input type="reset" value="취소">
			<input type="button" value="회원가입" onClick="register()">
			<input type="button" value="회원목록보기" onClick="memberList()">
		</td>
	</tr>
</table>
</form>