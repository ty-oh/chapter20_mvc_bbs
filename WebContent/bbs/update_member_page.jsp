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
		.not_null {
			color: red;
		}
		input[type="button"],
		input[type="reset"]  {
			font-size: 15px;
			font-color: #0078aa;
			font-weight: bold;
			border: 2px solid #0078aa;
			background-color: white;
		}
	</style>
	<script type="text/javascript">
		var update = function(f) {

		}
	</script>
</head>
<body>
	<div>
		<h2>회원 가입</h2>
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<td>
							<input type="text" name="id" value="${member.m_id }" size="30" readonly="readonly"/>
							<span class="not_null">* 필수 입력</span>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input type="password" name="pwCheck" size="30" />
							<span class="not_null">* 필수 입력</span>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="name" value="${member.m_name }" size="30" />
							<span class="not_null">* 필수 입력</span>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" name="email" value="${member.m_email }" size="30" />
							<span class="not_null">* 필수 입력</span>
						</td>
					</tr>
					<tr>
						<th>자기소개</th>
						<td>
							<textarea rows="10" cols="80" name="self" placeholder="내용을 입력해주세요." style="resize:none">
								${member.m_self }
							</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="btn">							
								<input type="button" value="정보 수정" onclick="update(this.form)"/>&nbsp;&nbsp;
								<input type="reset" value="다시작성">&nbsp;&nbsp;
								<input type="button" value="목록으로 이동" onclick="location.href='../index.jsp'">
								<input type="hidden" name="cmd" value="update_member" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>