<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		 div {
		    width: 800px;
		    margin:auto;
		    text-align: center;   
		 }
		 table {
		    width: 800px;
		    border-collapse: collapse;   
		 }
		 td, th {
		    border : 1px solid #1e90ff;
		    padding: 10px;
		 }
		 thead { 
		    background-color: #0078aa;
		    color: white;   
		 }
		 th:nth-of-type(1) {
		    width:50px;
		 }
		 th:nth-of-type(2) {
		    width:300px;
		 }
		 th:nth-of-type(3) {
		    width:100px;
		 }
		 th:nth-of-type(4) {
		    width:100px;
		 }   
		 th:nth-of-type(5) {
		    width:50px;
		 }   
		 h1 {
		    color : #0078aa;   
		 }
		 a.view {
		    text-decoration: none;
		    color : black;
		 }
		 a.view:hover {
		    font-weight:bold;
		    color : tomato;
		 }
		 ul.paging {
		    list-style-type: none;
		    overflow: hidden;
		    margin: auto;
		    width:350px;
		 }
		 ul.paging li {
		    float: left;
		    margin:20px;
		    color: #0078ff;
		 
		 }
		 ul.paging li a{
		    font-weight: bold;
		    display : block;
		    text-decoration: none;
		    color:  #0078ff;
		 }
		 ul.paging a:hover {
		     background: #1e90ff;
		     color: white;       
		 }
		 ul.paging li.disable {
		    color: silver;
		 }
		 ul.paging li.now{
		    color: tomato;
		    font-weight: bold;
		 }
		 tfoot tr {
		    margin: auto;
		 }
		 .disable {
		    color: silver;
		 }
		 .now{
		    color: tomato;
		    font-weight: bold;
		 }
		 .write_btn {
			font-size: 15px;
			font-color: #0078aa;
			font-weight: bold;
			border: 2px solid #0078aa;
			background-color: white;
		 }
	</style>
	<script type="text/javascript">
		var insert_page = function(f) {
			f.action='/chapter20_mvc_bbs/BBSController';
			f.submit();
		}
	</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="wrap">
		<h1>BBS 게시판</h1>
		<form>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list }">
							<tr>
								<td colspan="5">게시물이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="vo" items="${list }">
								<tr>
									<td>${vo.b_idx}</td>
 									<td><a class="view" href="/chapter20_mvc_bbs/BBSController?cmd=view&b_idx=${vo.b_idx }&currentPage=${pvo.nowPage}" onclick="viewPage(${vo.b_idx })">${vo.title}</a></td>
									<td>${vo.writer}</td>
									<td>${vo.reg_date}</td>
									<td>${vo.hit}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
							<%-- 페이징 처리예정 --%>		
							<%-- 1. 이전 버튼 --%>				
							<c:choose>
								<c:when test="${pvo.beginBlock <= pvo.pagePerBlock }">
										<span class ="disable"> ◀ &nbsp; </span>
								</c:when>
								<c:otherwise>
										<a class="view" href="/chapter20_mvc_bbs/BBSController?cmd=allList&currentPage=${pvo.beginBlock - 1 }"> ◀ &nbsp; </a>
								</c:otherwise>
							</c:choose>		
							<%-- 2. 페이지 번호 --%>			
							<c:forEach var="p" begin="${pvo.beginBlock }" end="${pvo.endBlock }" step="1">
								<c:choose>
									<c:when test="${p eq pvo.nowPage }">
										<span class="now">${p }&nbsp;</span>
									</c:when>
									<c:otherwise>
										 <a class="view" href="/chapter20_mvc_bbs/BBSController?cmd=allList&currentPage=${p}"> ${p }&nbsp;</a>
									</c:otherwise>
								</c:choose>	
							</c:forEach>		
							<%-- 3. 다음 버튼 --%>						
							<c:choose>
								<c:when test="${pvo.endBlock >= pvo.totalPage  }">
									<span class ="disable"> &nbsp;▶ </span>
								</c:when>
								<c:otherwise>
									<a class="view" href="/chapter20_mvc_bbs/BBSController?cmd=allList&currentPage=${pvo.beginBlock + pvo.pagePerBlock }" > &nbsp;▶ </a>
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
				</tfoot>
			</table>
			<p>
				<c:if test="${not empty member }">
					<input type="button" class="write_btn" value="게시글 작성" onclick="insert_page(this.form)">
					<input type="hidden" name="cmd" value="insert_page" />
				</c:if>
			</p>
		</form>
	</div>
</body>
</html>