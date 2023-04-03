<%-- 
    Document   : customer_detail
    Created on : Mar 15, 2023, 12:24:01 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid px-4">
    <h1 class="my-4">Thông tin khách hàng</h1>
    <div class="row">
        <div class="col-lg-6">
            <div class="table-outline">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Tên khách hàng: </p><p>${customer.getTenKhachHang()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày sinh: </p><p>${customer.getNgaySinhKH()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Giới tính: </p><p>${customer.getGioiTinh()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Địa chỉ: </p><p>${customer.getDiaChiKH()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Email: </p><p>${customer.getEmailKH()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số điện thoại: </p><p>${customer.getSoDienThoai()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Nghề nghiệp: </p><p>${customer.getNgheNghiep()}</p></div></li>

                </ul>
            </div>
            <a href="#createBankAccount" data-toggle="modal" class="btn btn-outline-success my-5" style="font-size: 16px">Mở tài khoản</a>
        </div>

        <div class="col-lg-6">  
            <div class="table-outline">
                <c:forEach var="user" items="${bankAccounts}">  
                    <div class="card border-dark mb-3" >
                        <div class="card-header">Tài khoản</div>
                        <div class="card-body text-dark">
                            <h5 class="card-title">Số dự tài khoản</h5>
                            <p class="card-text"><fmt:formatNumber value="${user.getSoDuTaiKhoan()}" pattern="###,### VNĐ"/></p>
                            <h5 class="card-title">Trạng thái tài khoản</h5>
                            <p class="card-text">${user.getTrangThaiTaiKhoan()==0 ? "Khóa" : "Đang hoạt động"}</p>
                            <h5 class="card-title">Ngày mở tài khoản</h5>
                            <p class="card-text"><fmt:formatDate value="${user.getNgayMoTaiKhoan()}" pattern="dd/MM/yyyy"/></p>
                            <h5 class="card-title">Ngày đóng tài khoản</h5>
                            <p class="card-text"><fmt:formatDate value="${user.getNgayDongTaiKhoan()}" pattern="dd/MM/yyyy"/></p>
                        </div>                       
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="createBankAccount" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="addBankAccount">
                        <div class="modal-header">
                            <h4 class="modal-title">Mở tài khoản ngân hàng</h4>

                        </div>
                        <div class="modal-body">
                            <p>Xác nhận mở tài khoản ngân hàng?</p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                            <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                            <input type="hidden" value="${customer.getIdKhachHang()}" name="customerId" id="customerId">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


