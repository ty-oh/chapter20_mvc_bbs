<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div{
			width: 400px;
			margin: auto; 
		}
		h2 {
			margin-top: 100px;
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
		#btn input {
			margin-left: 10px;
			font-size: 15px;
			font-color: #0078aa;
			font-weight: bold;
			border: 2px solid #0078aa;
			background-color: white;
		}
		.not_null {
			color: red;
		}
	</style>
	<script type="text/javascript">
		var login = function(f) {
			if (f.id.value=='') {
				alert('아이디를 입력하세요.');
				return;
			}
			if (f.pw .value=='') {
				alert('아이디를 입력하세요.');
				return;
			}
			
			f.action = '/chapter20_mvc_bbs/MemberController';
			f.submit();
		}
	</script>
</head>
<body>
	<div>
		<h2>로그인</h2>
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id" size="30" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pw" size="30" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="btn">							
								<input type="button" value="로그인" onclick="login(this.form)"/>&nbsp;&nbsp;
								<input type="hidden" name="cmd" value="login" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>