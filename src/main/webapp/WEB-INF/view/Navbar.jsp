<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-xl navbar-dark bg-dark sticky-top">
	<div class="container-fluid">
		<button class="navbar-toggler d-xl-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="true"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/"><img
			class="img-logo" src="${pageContext.request.contextPath}/resources/img/Logo_01.png" alt="Logo"></a>
		<ul class="navbar-nav  mt-2 mt-lg-0 icon-user-phone">
			<li class="nav-item">
			<a  class="nav-link bi bi-cart btn-person" id="icon-cart" 
			href="${pageContext.request.contextPath}/giohang"></a>
				<!-- <button class="nav-link bi bi-cart btn-person" id="icon-cart"
					onclick="go()"></button> -->
			</li>
			<li class="nav-item">
				<button type="button" class="nav-link bi bi-person btn-person"
					data-toggle="modal" data-target="#ModalLogin">
					</a>
			</li>
		</ul>

		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav  mt-2 mt-lg-0 nav-phone">
				<li class="nav-item">
					<form class="form-inline my-2 my-lg-0" id="form-search2">
						<input class="form-control mr-sm-2" id="input-search2" type="text"
							placeholder="VD: Trái cây nhập khẩu"> <span
							class="nav-link bi bi-search" id="search2"></span>
					</form>
				</li>
			</ul>
			<ul class="navbar-nav mr-auto ml-auto mt-2 mt-lg-0 navcenter">
				<li class="nav-item"><a class="nav-link" id="navbar-btn-home"
					href="${pageContext.request.contextPath}/">Trang Chủ</a></li>
				<li class="nav-item "><a class="nav-link" id="navbar-btn-traicaynhapkhau"
					href="#">Trái cây nhập khẩu</a></li>
				<li class="nav-item dropdown" id="navbar-btn-quatang"><a
					class="nav-link dropdown-toggle" href="#"
					id="dropdownId" aria-haspopup="true" aria-expanded="false">Sản Phẩm Khác</a>
					<div class="dropdown-menu" aria-labelledby="dropdownId">
						<a class="dropdown-item" href="#">Giỏ Trái Cây</a>
						<a class="dropdown-item" href="#">Hộp Quà tặng trái cây</a> 
						<a class="dropdown-item" href="#">Khay Trái Cây</a>
					</div></li>
				
				<li class="nav-item "><a class="nav-link" id="navbar-btn-dichvu"
					href="${pageContext.request.contextPath}/dichvu">Dịch Vụ</a></li>
				<li class="nav-item"><a class="nav-link" id="navbar-btn-gioithieu"
				href="${pageContext.request.contextPath}/vevinfruits">Về
						VinFruits</a></li>
			</ul>
			<ul class="navbar-nav  mt-2 mt-lg-0 nav-pc">
				<li class="nav-item input-search">
					<form class="form-inline my-2 my-lg-0" id="form-search">
						<input class="form-control mr-sm-2" id="input-search" type="text"
							placeholder="VD: Trái cây nhập khẩu"> <span
							class="nav-link bi bi-search" id="search"></span>
					</form>
				</li>
				<li class="nav-item">
					<a class="nav-link bi bi-cart btn-person-cart-pc"
					 id="icon-cart-pc" href="${pageContext.request.contextPath}/giohang"></a>
					<%-- <button class="nav-link bi bi-cart btn-person-cart-pc"
						id="icon-cart-pc" href="${pageContext.request.contextPath}/giohang"></button> --%>
				</li>
				<li class="nav-item">
					<button type="button" class="nav-link bi bi-person btn-person-pc"
						id="btnLogin">
						</a>
				</li>
			</ul>

		</div>
	</div>
</nav>