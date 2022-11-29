<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		var update = function(f) {
			if (f.title.value == '') {
				alert('제목을 입력해주세요.');
				return;
			}
			if (f.pw.value == '') {
				alert('비밀번호 입력해주세요.');
				return;
			}
			if (f.content.value == '') {
				alert('내용을 입력해주세요.');
				return;
			}
			
			var isPwCorrect = f.pw.value == "${bbsInfo.pw}";
			if (!isPwCorrect) {
				alert('비밀번호가 맞지 않습니다.');
				return;
			}
			
			f.action="/chapter20_mvc_bbs/BBSController";
			f.submit();
		}
		
		var view_all = function(f) {
			f.action='index.jsp';
			f.submit();
		}
	</script>

</head>
<body>
	<div>
		<h2>게시글 수정</h2>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<th>작성자</th>
						<td>
							${bbsInfo.writer }
							<input type="hidden" name="writer" value="${bbsInfo.writer }">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" size="80" value="${bbsInfo.title }"/>
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input type="password" name="pw" size="80" />
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="filename">
							<c:choose>
								<c:when test="${bbsInfo.filename eq null }">
									[ 기존 첨부 파일 : 없음 ]
								</c:when>
								<c:otherwise>
									[ 기존 첨부 파일 : ${bbsInfo.filename } ]
									<input type="hidden" name="oldfile" value="${bbsInfo.filename }">
								</c:otherwise>
							</c:choose>
							<a href="#">${bbsInfo.filename }</a>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="10" cols="80" name="content" placeholder="내용을 입력해주세요." style="resize:none">${bbsInfo.content}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="btn">							
								<input type="button" value="게시글 수정" onclick="update(this.form);"/>&nbsp;&nbsp;
								<input type="reset" value="다시 작성" />&nbsp;&nbsp;
								<input type="button" value="목록으로 이동" onclick="location.href='index.jsp?currentPage=${currentPage}'" />
								<!-- updqte.jsp에게 넘겨줄 파라미터를 hidden으로 처리 -->
								<input type="hidden" name="currentPage" value="${currentPage }" />
								<input type="hidden" name="b_idx" value="${bbsInfo.b_idx }" />
								<input type="hidden" name="cmd" value="update" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>