<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		header {
			width: 800px;
			margin: auto;
			position: relative;
		}
		.member_btn {
			display: inline-block;
			position: absolute;
			right: 0px;
		}
		.member_btn input {
			float: right;
			margin-left: 10px;
			font-size: 15px;
			font-color: #0078aa;
			font-weight: bold;
			border: 2px solid #0078aa;
			background-color: white;
		}
	</style>
</head>
<body>
	<script type="text/javascript">
		var join_member_page = function() {
			location.href = "/chapter20_mvc_bbs/MemberController?cmd=join_member_page";
		}
		var login_page = function() {
			location.href = "/chapter20_mvc_bbs/MemberController?cmd=login_page";
		}
		var logout = function() {
			location.href = "/chapter20_mvc_bbs/MemberController?cmd=logout";
		}
		var update_member_page = function() {
			location.href = "/chapter20_mvc_bbs/MemberController?cmd=update_member_page";
		}
	</script>
	<header>
		<div class="member_btn">
			<c:choose>
				<c:when test="${not empty member}">
					<input type="button" value="로그아웃" onclick="logout()">
					<input type="button" value="회원정보" onclick="update_member_page()">
				</c:when>
				<c:otherwise>
					<input type="button" value="로그인" onclick="login_page()">
					<input type="button" value="회원가입" onclick="join_member_page()">
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>