<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		var isLoginSuccess = ${not empty member};
		
		if (isLoginSuccess) {
			location.href="../index.jsp";
			
		} else {
			alert('일치하는 회원정보가 없습니다.');
			history.back();
			
		}
	</script>
</head>
<body>

</body>
</html>