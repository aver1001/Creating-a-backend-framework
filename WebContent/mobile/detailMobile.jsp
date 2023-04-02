<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>

	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
	
	  <h2>핸드폰 상세 정보</h2>
  	  <a class="btn btn-danger" href="#">삭제</a>
	  <table class="table table-striped">
	      <tr>
	        <td>고유번호</td><td></td>
	      </tr>
	      <tr>
	        <td>모델명</td><td></td>
	      </tr>
	      <tr>
	        <td>가격</td><td></td>
	      </tr>
	      <tr>
	        <td>제조사</td><td></td>
	      </tr>
	  </table>
	
	</div>
<%@ include file="/include/footer.jsp" %>