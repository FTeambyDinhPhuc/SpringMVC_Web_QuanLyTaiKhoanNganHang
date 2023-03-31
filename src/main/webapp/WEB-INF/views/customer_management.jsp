<%-- 
    Document   : customer_management
    Created on : Mar 14, 2023, 10:16:16 PM
    Author     : dinhp
--%>

<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid px-4">
    <h1 class="my-4">Quản lý khách hàng</h1>
    <div class="d-flex justify-content-between align-items-center">
        <form class="d-flex align-items-center" style="width: 450px">
            <input type="text" class="form-control my-4 mr-4" name="searchcustomer" autocomplete="off"   placeholder="Nhập vào số căn cước"></input>
            <button type="submit" class="mybuton-primary">Tìm kiếm</button>
        </form>
        <a href="#createCustomerModal" data-toggle="modal" class="btn btn-outline-success" style="font-size: 16px">Thêm khách hàng</a>
    </div>
    <h3 class="text-danger" id="tb">${message}</h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Tên khách hàng</th>
                <th scope="col">Ngày sinh</th>
                <th scope="col">Giới tính</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">Email</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Căn cước</th>
                <th scope="col">Nghề nghiệp</th>
                <th scope="col">Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${khachhangs}">
                <tr>               
                    <td>${user.getTenKhachHang()}</td>
                    <td>${user.getNgaySinhKH()}</td>
                    <td>${user.getGioiTinh()}</td>
                    <td>${user.getDiaChiKH()}</td>
                    <td>${user.getEmailKH()}</td>
                    <td>${user.getSoDienThoai()}</td>
                    <td>${user.getCccd()}</td>
                    <td>${user.getNgheNghiep()}</td>
                    <td>
                        <a href="./customer_detail" class="mybuton-icon-detail px-3"><i class="fa-solid fa-circle-info" title="Detail"></i></a>
                        <a href="#editCustomerModal" data-toggle="modal" class="mybuton-icon-edit px-3"><i class="fa-solid fa-pen-to-square " data-toggle="tooltip" title="Edit"></i></a>
                        <a href="#deleteCustomerModal" data-toggle="modal" class="mybuton-icon-delete px-3"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div id="createCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="input-boxes" modelAttribute="customer" action="customer_management.htm"method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Thêm khách hàng</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên khách hàng</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="TenKhachHang"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messname} </h3>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input type="date" class="form-control" required="required" autocomplete="off"  name="NamSinh"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${message1} </h3>
                        <div class="form-group">
                            <label>Giới tính</label>
                            <select class="form-control"required="required" autocomplete="off"  name="GioiTinh" >
                                <!--Lấy từ db-->
                                <option>Nam</option>
                                <option>Nữ</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Địa chỉ</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="DiaChi"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messdir} </h3>
                        <div class="form-group">
                            <label>Căn cước công dân</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="CCCD"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messcccd} </h3>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required="required" autocomplete="off"  name="Email"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messemail} </h3>
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input type="tel" class="form-control" required="required" autocomplete="off"  name="SoDienThoai"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messphone} </h3>
                        <div class="form-group">
                            <label>Nghề nghiệp</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="NgheNghiep"></input>
                        </div>
                        <h3 style="color: red" id="tb1" >${messmajor} </h3>

                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="editCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Sửa thông tin khách hàng</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên khách hàng</label>
                            <input type="text" class="form-control" required="required" name="fullname"></input>
                        </div>

                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input type="date" class="form-control" required="required" name="birthday"></input>
                        </div>
                        <div class="form-group">
                            <label>Giới tính</label>
                            <select class="form-control"required="required" name="sex" >
                                <!--Lấy từ db-->
                                <option>Nam</option>
                                <option>Nữ</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Địa chỉ</label>
                            <input type="text" class="form-control" required="required" name="address"></input>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required="required" name="email"></input>
                        </div>
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input type="tel" class="form-control" required="required" name="numberphone"></input>
                        </div>
                        <div class="form-group">
                            <label>Nghề nghiệp</label>
                            <input type="text" class="form-control" required="required" name="job"></input>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="deleteCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/customer_management/{customerId}" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa khách hàng</h4>
                        <input type="hidden" name="customerId" value="${user.getIdKhachHang()}">
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa khách hàng này?</p>
                        <p class="text-warning"><small>Không thể khôi phục khi đã xóa</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận">                       
                        </input>
                    </div>
                </form>
            </div>
        </div>
    </div>


</div>
<jsp:include page="footer.jsp" />
<script>
    window.onload = function () {
        $("tb").innerText = '';
    };

    $(document).ready(function () {
        setTimeout(function () {
            $("#tb").css("display", "none");
        }, 2000); // 2 seconds
    });
</script>
