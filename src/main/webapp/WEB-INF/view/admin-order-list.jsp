<%@page import="com.se.ecofruits.entity.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.se.ecofruits.entity.Status"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.se.ecofruits.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/css/adminlte.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/css/OverlayScrollbars.min.css">
</head>
<body>
	<jsp:include page="partial/navbar.jsp"></jsp:include>
	<jsp:include page="partial/asidebar.jsp"></jsp:include>
	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<jsp:include page="partial/header.jsp">
							<jsp:param name="name" value="List order" />
						</jsp:include>
					</div>
					<!-- /.col -->
					<div class="col-sm-6">
						<jsp:include page="partial/breadcrumb.jsp">
							<jsp:param name="item_sub" value="Catalog" />
							<jsp:param name="item_main" value="Order" />
						</jsp:include>
					</div>
				</div>
			</div>
		</div>
			<div class="content">
				<div class="content-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header" aria-expanded="true"
									data-target=".collapse" data-card-widget="collapse"
									style="cursor: pointer;">
									<div class="card-title">
										<i class="fa-solid fa-magnifying-glass"></i> Search
									</div>
									<div class="card-tools">
										<button data-card-widget="collapse" type="button"
											class="btn btn-tool">
											<i class="fas fa-minus"></i>
										</button>
									</div>
								</div>
								
									<div class="card-body collapse show">
									<form>
										<div class="row">
											<div class="col-6">
												<div class="form-group">
													<label>Phone:</label>
													<input type="text" name="phone" style="width: 100%;" placeholder="0xxx-xxx-xxx"
															class="form-control" value="<%= request.getParameter("phone") == null ? "" : request.getParameter("phone") %>" />
												</div>	
												<div class="form-group">
													<label>Status:</label> 
													<select class="form-control" name="statusID" style="width: 100%;">
														<%
															int statusID =  (int)request.getAttribute("statusID"); 
															int cnt = 0;
														%>
														<option <%= ( statusID == cnt ? "" : "selected=\"selected\"")%> value="0">Tất cả</option>
														<c:forEach items="${status}" var="status">
																<%cnt++; %>
															<option <%= ( statusID == cnt ? "selected=\"selected\"" : "")%> value="${ status.statusID }" >${ status.name }</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group">
													<label>Filter:</label> 
													<select class="form-control" name="filter" style="width: 100%;">
														<option <%= ( (int)request.getAttribute("filter") == 1 ? "selected=\"selected\"" : "")%> value="1">Hóa đơn mới nhất</option>
														<option <%= ( (int)request.getAttribute("filter") == 2 ? "selected=\"selected\"" : "")%> value="2">Tổng tiền tăng dần</option>
														<option <%= ( (int)request.getAttribute("filter") == 3 ? "selected=\"selected\"" : "")%> value="3">Tổng tiên giảm dần</option>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="card-footer">
										<div class="col-12 text-center">
											<button class="btn btn-primary btn-search"
												style="width: 40%;" type="submit">
												<i class="fa-solid fa-magnifying-glass"></i> Search
											</button>
										</div>
									</div>
								</form>
							</div>
							
						<div class="card">
							<div class="card-header">
								<div class="card-title">Danh sách hóa đơn</div>
							</div>
							<div class="card-body">
								<div>
									<label>Show 
										<select name="example_length" aria-controls="example">
											<option value="10">10</option>
											<option value="25">25</option>
											<option value="50">50</option>
											<option value="100">100</option>
										</select> entries
									</label>
								</div>
								<table class="table table-bordered table-hover data-table-form">
									<thead>
										<tr>
											<th style="width: 70px;" class="text-center"><input type="checkbox"></th>
											<th class="text-capitalize">Create date</th>
											<th class="text-capitalize">Customer name</th>
											<th class="text-capitalize">Phone</th>
											<th class="text-capitalize">Total</th>
											<th class="text-capitalize">Discount</th>
											<th class="text-capitalize">Status</th>
											<th style="width: 130px;" class="text-capitalize text-right">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orders.pageList}" var="order">
											<%-- <c:url var="deleteOrder" value="${pageContext.request.contextPath}/admin/order/updatestatus?orderID=${order.orderID}&statusID=4"></c:url>
											 --%>
											 <c:url var="DetailOrder" value="/admin/order/detail">
												<c:param name="orderID" value="${order.orderID}" />
											</c:url>
											<tr>
												<td class="text-center check-item"><input type="checkbox"></td>
												<td>${ order.createdDate }</td>
												<td>${ order.user.fullName}</td>
												<td>${ order.shipPhone }</td>
												<td><fmt:formatNumber value = "${ order.total }" type = "number" maxFractionDigits = "0"/> VND</td>
												<td><fmt:formatNumber value = "${ order.discount }" type = "number" maxFractionDigits = "0"/> VND</td>
												<td style="margin: auto;">
													<form action="${pageContext.request.contextPath}/admin/order/updatestatus/" method="post">
														<input type="hidden" name="orderID" value="${order.orderID}">
														<select class="form-control" name="statusID" style="width: 100%; margin-bottom:2%;">
															<c:forEach items="${status}" var="status">
																<option ${ order.status.statusID == status.statusID ? 'selected="selected"' : '' } value="${ status.statusID }">${ status.name }</option>
															</c:forEach>
														</select>
														${((order.status.statusID != 4 && order.status.statusID != 5) ? '<input type="submit" value="Cập nhật" class="btn btn-primary" style="width: 100%; margin-top:2%;">' : '')}
													</form>
												</td>
												<td>
													<a id="DetailOrder" href="${ DetailOrder }"
													type="button" class="btn btn-warning mr-2" style="width: 90%; margin-bottom:2%; color:white;">
													<i class="fa-regular fa-file-lines"></i> Chi tiết</a> 
													<%-- ${((order.status.statusID != 4 && order.status.statusID != 5) ? '<a id="deleteOrder" href="${ deleteOrder}" class="btn btn-danger" style="width: 90%; margin-top:2%;"> <i class="fa-solid fa-trash"></i> Hủy đơn</a>' : '')}	
													 --%>	
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
												
							<div class="card-footer">
								<div class="row">
									<div class="col-6">Pagination</div>
										<div class="col-6">
											<jsp:include page="partial/pagination.jsp">
											<jsp:param name="pageCount" value="${ pageCount }" />
											<jsp:param name="pageCurrent" value="${ pageCurrent }" />
											<jsp:param name="url" value="${ url }" />
										</jsp:include>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</div>
	<jsp:include page="partial/footer.jsp"></jsp:include>
	<jsp:include page="partial/control-sidebar.jsp"></jsp:include>
		<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	 	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/js/OverlayScrollbars.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/js/adminlte.min.js"></script>
	

</body>
</html>