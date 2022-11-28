<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div{
			width: 800px;
			margin: auto; 
		}
		table {
			width: 100%;
			text-align: left;
			border-collapse: collapse;
		}
		td, th {
			border : 1px solid #0078aa;
			padding: 10px;
		}
		th {
			background-color: #0078aa;
			color: #fff;
			text-align: center;
		}
		#btn {
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		var update_page = function(f) {
			f.action='update_page.jsp';
			f.submit();
		}
		var remove_page = function(f) {
			f.action='remove_page.jsp';
			f.submit();
		}
		var view_all = function(f) {
			f.action='index.jsp';
			f.submit();
		}
		var insert_comment= function(f) {
			f.action='insert_comment.jsp';
			f.submit();
		}
		var remove_comment = function(pw, c_idx, b_idx, currentPage) {
			var pwCheck = prompt('비밀번호를 입력하세요.');
			var isPwCorrect = pw == pwCheck;
			if (!isPwCorrect) {
				alert('비밀번호가 틀립니다.');
				return;
			}
			
			location.href='remove_comment.jsp?c_idx=' + c_idx + '&b_idx=' + b_idx + '&currentPage=' + currentPage;
		}
	</script>
</head>
<body>
	
</body>
</html>