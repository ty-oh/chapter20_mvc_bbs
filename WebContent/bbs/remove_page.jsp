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
		var removeBbs = function(f) {
			var isPwCorrect = f.pw.value == "${bbsInfo.pw }";
			if (!isPwCorrect) {
				alert('비밀번호가 맞지 않습니다.');
				return;
			}
			
			f.action = 'remove.jsp';
			f.submit();
		}
		
		var back = function() {
			history.back();
		}
	</script>
</head>
<body>
	<div>
		<h2>${bbsInfo.writer }의 방명록을 삭제하시겠습니까?</h2>
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>작성자</th>
						<td>
							${bbsInfo.writer }
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${bbsInfo.title }</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pw" size="80"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="btn">
								<input type="button" value="삭제" onclick="removeBbs(this.form)"/>&nbsp;&nbsp;
								<input type="button" value="취소" onclick="back()">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>