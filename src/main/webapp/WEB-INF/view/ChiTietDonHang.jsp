<%@page import="java.util.List"%>
<%@page import="com.se.ecofruits.entity.Order_Detail"%>
<%@page import="com.se.ecofruits.entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Trang chủ - Vinfruts</title>
	<meta name=”viewport” content=” width=device-width, initial-scale=1″>
	<meta charset="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<!-- <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css" /> -->
	<!-- <script src="../bootstrap/jquery/jquery-3.6.0.min.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script> -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Navbar.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Footer.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Home.css" type="text/css">
	<!--  <script src="../js/data.js"></script>
	        <script src="../js/functionsHandle.js"></script>
	        <script src="../js/regex.js"></script>
	        <script src="../js/search.js"></script>  -->

</head>
<body>
	<div class="wrapper">
		<jsp:include page="Navbar.jsp"></jsp:include>
		<div class="container-fluid">
			<%
			 	Order o = (Order)request.getAttribute("ORDER");
				List<Order_Detail> od = (List<Order_Detail>)request.getAttribute("ORDERDETAIL");
			%>
			
			<p> <%= o.getOrderID()%> </p>
			
			<p> <%= o.getShipPhone()%> </p>
			<p> <%= o.getShipAddress()%> </p>
			<p> <%= o.getComment()%> </p>
			
			<c:forEach items="${ORDERDETAIL}" var="detail">
			<p> ${detail.product.name} </p>
			<p> ${detail.amount} </p>
			<p> ${detail.price} </p>
			</c:forEach>
		

		</div>
		<jsp:include page="Footer.jsp"></jsp:include>

	</div>
</body>
</html>