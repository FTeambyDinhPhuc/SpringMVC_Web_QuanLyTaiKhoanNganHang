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
            <input type="text" class="form-control my-4 mr-4" required="required" name="searchcustomer"autocomplete="off"  placeholder="Nhập vào số căn cước"></input>
            <button type="submit" class="mybuton-primary">Tìm kiếm</button>
        </form>
        <a href="#createCustomerModal" data-toggle="modal" class="btn btn-outline-success" style="font-size: 16px">Thêm khách hàng</a>
    </div>
    <p class="text-danger">Không tìm thấy khách hàng</p>
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
            <c:forEach var="custommer" items="${khachhangs}">
                <tr>
                    <td>${custommer.getTenKhachHang()}</td>
                    <td><fmt:formatDate value="${custommer.getNgaySinhKH()}" pattern="dd/MM/yyyy"/></td>
                    <td>
                        <c:if test="${custommer.isGioiTinh()}">
                            Nam
                        </c:if>
                        <c:if test="${not custommer.isGioiTinh()}">
                            Nữ
                        </c:if>
                    </td>
                    <td>${custommer.getDiaChiKH()}</td>
                    <td>${custommer.getEmailKH()}</td>
                    <td>${custommer.getSoDienThoai()}</td>
                    <td>${custommer.getCccd()}</td>
                    <td>${custommer.getNgheNghiep()}</td>
                    <td>
                        <a href="./customer_detail" class="mybuton-icon-detail px-3"><i class="fa-solid fa-circle-info" title="Detail"></i></a>
                        <a href="#editCustomerModal" data-toggle="modal" class="mybuton-icon-edit px-3"><i class="fa-solid fa-pen-to-square " data-toggle="tooltip" title="Edit"></i></a>
                        <a href="#deleteCustomerModal"data-toggle="modal" class="mybuton-icon-delete px-3"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>
<!--                    <a href="/deleteCustomerModal/${customer.getId()}" class="mybutton-icon-delete px-3" data-toggle="modal"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>-->

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



    <div id="createCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Thêm khách hàng</h4>
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
                            <label>Căn cước công dân</label>
                            <input type="text" class="form-control" required="required" name="cccd"></input>
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
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa khách hàng</h4>

                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn khách hàng này?</p>
                        <p class="text-warning"><small>Không thể khôi phục khi đã xóa</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
 