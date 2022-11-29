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
		var update_page = function(f) {
			f.action='update_page.jsp';
			f.submit();
		}
		var remove_page = function(f) {
			f.action='remove_page.jsp';
			f.submit();
		}
		var view_all = function(f) {
			f.action='/chapter20_mvc_bbs/BBSController?cmd=allList';
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
	<div>
		<form action="">		
			<h2>${bbsInfo.writer }의 게시글</h2>
			<table>
				<tbody>
					<tr>
						<th>작성자</th>
						<td>${bbsInfo.writer }</td>
					</tr>
					<tr>
						<th>IP</th>
						<td>${bbsInfo.ip }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${bbsInfo.title }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${bbsInfo.hit }</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<c:choose>
							<c:when test="${bbsInfo.filename eq null }">
								<td>첨부파일이 없습니다.</td>
							</c:when>
							<c:otherwise>
								<td><a href="download.jsp?path=upload&filename=${bbsInfo.filename }">${bbsInfo.filename }</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="10" cols="80" name="content" placeholder="내용을 입력해주세요." readonly>${bbsInfo.content }</textarea></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${bbsInfo.reg_date }</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="btn">
								<input type="button" value="게시글 수정하기" onclick="update_page(this.form)"/>&nbsp;&nbsp;
								<input type="button" value="게시글 삭제하기" onclick="remove_page(this.form)">&nbsp;&nbsp;
								<input type="button" value="목록으로 이동" onclick="view_all(this.form)">
								<input type="hidden" name="currentPage" value="${currentPage }">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<br/><br/><br/>

		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>댓글 작성자</th>
						<td><input type="text" name="writer" /></td>
						<th>비밀번호</th>
						<td><input type="password" name="pw" /></td>
					</tr>
					<tr>
						<th>댓글 내용 </th>
						<td colspan="3">
							<textarea rows="3" cols="80" name="content" placeholder="댓글을 입력하세요." style="resize:none;"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" id="btn">
							<input type="button" value="댓글 등록" onclick="insert_comment(this.form)">&nbsp;&nbsp;
							<input type="reset" value="다시 작성">
							<input type="hidden" name="b_idx" value="${bbsInfo.b_idx }">&nbsp;&nbsp;
							<input type="hidden" name="currentPage" value="${currentPage }">&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
 		<br/><br/><br/>

		<form method="post" action="remove_comment.jsp">
			<table class="viewComment">
				<thead>
					<tr>
						<th id="num">번호</th>
						<th id="writer">작성자</th>
						<th id="con">내용</th>
						<th id="date">작성일</th>
						<th id="del">삭제</th>						
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty cList }">
							<c:forEach var="c" items="${cList }">
								<c:set var="cnt" value="${cnt + 1 }"/>
								<tr>
									<td>${cnt }</td>
									<td>${c.writer }</td>
									<td>${c.content }</td>
									<td>${c.reg_date }</td>
									<td><a href="#" onclick="remove_comment(${c.pw}, ${c.c_idx }, ${c.b_idx }, ${currentPage })">삭제</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">댓글이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>