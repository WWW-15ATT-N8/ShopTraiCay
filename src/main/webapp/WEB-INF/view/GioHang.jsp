<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Giỏ Hàng - Vinfruts</title>
<meta name=”viewport” content=” width=device-width, initial-scale=1″>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/GioHang.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/Navbar.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/Footer.css"
	type="text/css">
<!--  <script src="../js/data.js"></script>
        <script src="../js/functionsHandle.js"></script>
        <script src="../js/regex.js"></script>
        <script src="../js/search.js"></script> -->

</head>
<body>
	<div class="wrapper">

		<jsp:include page="./Navbar.jsp"></jsp:include>

		<div class="container-fluid">
			<div class="giohang">
				<div class="giohang-left">
					<form id="form-gh" style="display: block;">
						<table>
							<thead>
								<tr>
									<th colspan="2">Sản Phẩm</th>
									<th class="gh-price">Giá</th>
									<th class="amount">Số lượng</th>
									<th class="totalprice">Tổng cộng</th>
								</tr>
							</thead>

							<tbody id="giohang-chitiet">

							</tbody>
						</table>
					</form>
					<p id="thongbao-giohang" style="display: none;">Bạn không có
						sản phẩm nào trong giỏ</p>
					<button onclick="valueGH()" class="capnhat" id="capnhatgh">Cập
						nhật giỏ hàng</button>
					<button onclick="Freturn()" class="capnhat" id="quaylaiTrue">Quay
						Lại cửa hàng</button>
				</div>
				<div class="giohang-right">
					<table>
						<thead>
							<tr>
								<th colspan="2">Thanh Toán</th>
							</tr>
						</thead>
						<tbody id="pay">
							<tr>
								<td>Tổng cộng</td>
								<td id="total"></td>
							</tr>
							<tr>
								<td>Phí vận chuyển</td>
								<td id="feeship"><b>Giao hàng miễn phí</b></td>
							</tr>
							<tr>
								<td>Thuế VAT (10%)</td>
								<td id="fee-VAT"></td>
							</tr>
							<tr>
								<td>Các loại phí khác</td>
								<td id="otherfees"><b>0 ₫</b></td>
							</tr>
							<tr id="total">
								<td>Tổng tiền thanh toán</td>
								<td id="totalpay"></td>
							</tr>
						</tbody>
					</table>
					<button class="thanhtoan" id="btnthanhtoan"
						onclick="location.href = './ThanhToan.html'">Tiến hành
						thanh toán</button>
				</div>
			</div>
		</div>

		<jsp:include page="./Footer.jsp"></jsp:include>

	</div>

	<script>
            /* changeIconGH(); */
            var gh ;
            var str ="";
            var pay = 0 ;
            var slgh = 0;
            var vat = 0;

            if(localStorage.getItem("giohang") != null){
                gh = localStorage.getItem("giohang").split(",");
                for(var i = 0 ; i < 96 ;i++){
                    var sl = parseInt(gh[i]);
                    slgh += sl;
                    if( sl > 0){
                        total = (parseFloat(data[i].price)*sl)*1000;
                        pay += total;    
                        str += `<tr>
                            <td id='gh-pic' class="gh-tt"><img src="../picture/data_picture/${data[i].picture[0]}" alt="alt"></td> 
                            <td id='gh-name'>
                                <a href="./ChiTietSanPham.html">
                                    <p class="gh-name-watch">${data[i].name}</p>
                                </a>
                            </td>
                            <td class="gh-tt gh-price" id = "price${i}"><b>${Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data[i].price * 1000)}</b></td>
                            <td class="gh-tt amount" id = "gh-soluong">
                                <input type="button" value="-" id="down" onclick="changeValueGH(-1,${i})">
                                <input type="text" value="${sl}" class="soluong" id="soluong-${i}">
                                <input type="button" value="+" id="up" onclick="changeValueGH(1,${i})">
                            </td>
                            <td class="gh-tt totalprice" id = "totalprice${i}"><b>${ Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(total)}</b></td>
                        </tr>` ;
                        }   
                }
            }
            localStorage.setItem("soluongsp", slgh);
            if(slgh <= 0 ){
                document.getElementById('thongbao-giohang').style.display = 'block';
                document.getElementById('form-gh').style.display = 'none';
                document.getElementById('capnhatgh').style.display = 'none';
                document.getElementById('feeship').innerHTML = '<b>' + Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) + '</b>';
                document.getElementById("btnthanhtoan").disabled = true;
                document.getElementById("btnthanhtoan").style.backgroundColor = 'rgb(255, 243, 176)';
                document.getElementById('quaylaiTrue').id = 'quaylaiFalse';

            }
            else{
                document.getElementById('thongbao-giohang').style.display = 'none';
                document.getElementById('form-gh').style.display = 'block';
                document.getElementById('capnhatgh').style.display = 'inline';
                document.getElementById('feeship').innerHTML = "<b>Giao hàng miễn phí</b>";
                document.getElementById("btnthanhtoan").disabled = false;
                document.getElementById("btnthanhtoan").style.backgroundColor = 'gold';
            }
            vat = pay * 0.1 ;
            document.getElementById('fee-VAT').innerHTML =  '<b>' + Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(vat) + '</b>';
            document.getElementById('giohang-chitiet').innerHTML = str;
            document.getElementById('total').innerHTML = '<b>' + Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(pay) + '</b>';
            document.getElementById('totalpay').innerHTML = '<b>' + Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(pay + vat) + '</b>';

            
            function changeValueGH(x,id){
                id = 'soluong-' + String(id);
                var n = document.getElementById(id).value;
                if(!(parseInt(n)<=1 && x==-1))
                    document.getElementById(id).value = parseInt(n) + x ;
                else{
                    document.getElementById(id).value = 0 ;
                }
            }
            function valueGH(){
                x = document.getElementsByClassName('soluong');
                var gh = localStorage.getItem("giohang").split(",");
                var cnt = 0;
                for(var i = 0 ; i < x.length; i++){
                    var tmp = (x[i].id).split('-');
                    var id = tmp[1];
                    var n = document.getElementById('soluong-'+id).value;
                    gh[id] = n;
                    cnt += parseInt(n);
                }
                localStorage.setItem("giohang",gh);
                localStorage.setItem("soluongsp", cnt);
                location.href = './GioHang.html';    
            }
            function Freturn(){
                location.href = './TrangChu.html'
            }
        </script>
</body>
</html>