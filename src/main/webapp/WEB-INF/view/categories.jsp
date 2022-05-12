<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/css/adminlte.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/css/ion.rangeSlider.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/css/alt/adminlte.plugins.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/css/OverlayScrollbars.min.css">

</head>
<body class="font-sans antialiased hold-transition sidebar-mini">
	<div class="wrapper">
	<jsp:include page="partial/navbar.jsp"></jsp:include>
	<jsp:include page="partial/asidebar.jsp"></jsp:include>
	
	<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <jsp:include page="partial/header.jsp">
            	<jsp:param name="name" value="Category" />
            </jsp:include>
          </div><!-- /.col -->
          <div class="col-sm-6">
           <jsp:include page="partial/breadcrumb.jsp">
            	<jsp:param name="item_1" value="Home" />
            	<jsp:param name="item_main" value="Category" />
            </jsp:include>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
	
	<div class="content">
      <div class="content-fluid">
        <div class="row">
          <div class="col-12">
            <!-- Search form -->
            <div class="card">
              <div class="card-header" aria-expanded="true" data-target=".collapse" data-card-widget="collapse" style="cursor: pointer;">
                <div class="card-title">
                  <i class="fa-solid fa-magnifying-glass"></i>
                 Search </div>
                <div class="card-tools">
                  <button data-card-widget="collapse" type="button" class="btn btn-tool">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
              </div>
              <div class="card-body collapse show" >
                <form @submit.prevent="search">
                  <div class="row">
                    <div class="col-6">
                      <div class="form-group">
                        <label>Name:</label>
                        <input v-model="params.name" type="search" class="form-control" style="width: 100%;" placeholder="Name">
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="form-group">
                        <label>Number Of Product:</label>
                          <div>
                            <input type="text" class="js-range-slider mb-3" name="my_range" value="" />
                          </div>
                        <!-- <Slider/> -->
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-12 text-center">
                      <button class="btn btn-primary btn-search" type="submit"> 
                        <i class="fa-solid fa-magnifying-glass"></i>
                        Search
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- Search form -->
            <!-- Table -->
            <div class="card">
              <div class="card-header">
                <div class="card-title">Table</div>
                <div class="card-tools">
                  <button class="btn btn-danger mr-2">Delete Selected</button>
                  <button class="btn btn-primary" @click="goToCreate">Add</button>
                  
                </div>
              </div>
              <div class="card-body">
                <div>
                  <label>Show 
                    <select  @change="changeTableLenght" name="example_length" aria-controls="example" class="">
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
                      <th style="width: 70px;" class="text-center"><input type="checkbox" @click="checkAll()" v-model="checkbox.isCheckedAll"> </th>
                      <th class="text-capitalize">
                        Name
                      </th>
                      <th class="text-capitalize">
                        Num Of Product
                      </th>
                    
                      <th style="width: 130px;" class="text-capitalize text-right">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${categories}" var="category">
                  	<tr>
                  		<td class="text-center check-item" >
                  			<input type="checkbox">
                  		</td>
                  		<td>${ category.name }</td>
                  		<td>${ category.products.size() }</td>
                  		<td>
                  			<button id="editCategory" type="button" class="btn btn-warning mr-2"><i class="fa-solid fa-pen-to-square"></i></button>
                        	<button id="deleteCaterory" class="btn btn-danger"><i class="fa-solid fa-trash"></i></button>
                  		</td>
                  	</tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="card-footer">
                <div class="row">
                  <div class="col-6">
                    Pagination
                  </div>
                  <div class="col-6">
                  <%-- ${categories.getCount} --%>
				<%-- <jsp:include page="partial/pagination.jsp">
						<jsp:param name="link" value="${ categories }" />
					</jsp:include> --%>
                  </div>
                </div>
              </div>
            </div>
            <!-- Table -->
          </div>
        </div>
      </div>
    </div>
    </div>
	<jsp:include page="partial/footer.jsp"></jsp:include>
	<jsp:include page="partial/control-sidebar.jsp"></jsp:include>
	
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/js/ion.rangeSlider.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/js/OverlayScrollbars.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.2.0/js/adminlte.min.js"></script>
		<script type="text/javascript">
		$(".js-range-slider").ionRangeSlider({
	        type: "int",
	        min: 0,
	        max: 100,
	        from: 0,
	        to: 100,
	        grid: true
	    });
		</script>
</body>
</html>