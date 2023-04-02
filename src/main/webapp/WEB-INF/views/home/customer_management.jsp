<%-- 
    Document   : customer_management
    Created on : Mar 14, 2023, 10:16:16 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="d-flex justify-content-between align-items-center">
        <form class="d-flex align-items-center" style="width: 450px">
            <input type="text" class="form-control my-4 mr-4" name="searchcustomer" autocomplete="off" placeholder="Nhập vào số căn cước"></input>
            <button type="submit" class="mybuton-primary">Tìm kiếm</button>
        </form>
        <a href="#createCustomerModal" data-toggle="modal" class="btn btn-outline-success" style="font-size: 16px">Thêm khách hàng</a>
    </div>
    <p class="warning-text">${messageCustomer}</p>
    <p class="success-text">${messageSuccessCustomer}</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Tên khách hàng</th>
                <th scope="col">Ngày sinh</th>
                <th scope="col">Giới tính</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">Email</th>
                <th scope="col">Số điện thoại</th>
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
                    <td>${user.getNgheNghiep()}</td>
                    <td>
                        <a href="<c:url value="/home/customer_detail/?id=${user.getIdKhachHang()}"/>" class="mybuton-icon-detail px-3"><i class="fa-solid fa-circle-info" title="Detail"></i></a>
                        <a onclick="editCustomer(this)" id="btnEditCustomer" href="#editCustomerModal" data-toggle="modal" class="mybuton-icon-edit px-3"><i class="fa-solid fa-pen-to-square " data-toggle="tooltip" title="Edit"></i></a>
                        <a onclick="deleteCustomer(this)" id="btnDeleteCustomer" href="#deleteCustomerModal" data-toggle="modal" class="mybuton-icon-delete px-3"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>
                        <input type="hidden" id="idCustomer" value="${user.getIdKhachHang()}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div id="createCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="input-boxes" modelAttribute="customer" action="customer_management.htm" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Thêm khách hàng</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên khách hàng</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="TenKhachHang"></input>
                        </div>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input type="date" class="form-control" required="required" autocomplete="off"  name="NamSinh"></input>
                        </div>
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
                        <div class="form-group">
                            <label>Căn cước công dân</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="CCCD"></input>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required="required" autocomplete="off"  name="Email"></input>
                        </div>
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input type="tel" class="form-control" required="required" autocomplete="off"  name="SoDienThoai"></input>
                        </div>
                        <div class="form-group">
                            <label>Nghề nghiệp</label>
                            <input type="text" class="form-control" required="required" autocomplete="off"  name="NgheNghiep"></input>
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
                <form method="post" modelAttribute="customer" action="customer_management/edit">
                    <div class="modal-header">
                        <h4 class="modal-title">Sửa thông tin khách hàng</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên khách hàng</label>
                            <input path="tenKhachHang" id="tenKhachHang" type="text" class="form-control" required="required" name="tenKhachHang"></input>
                        </div>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input path="ngaySinhKH" id="ngaySinhKH" type="date" class="form-control" required="required" name="ngaySinhKH"></input>
                        </div>
                        <div class="form-group">
                            <label>Giới tính</label>
                            <select  path="gioiTinh" class="form-control"required="required" name="gioiTinh" >
                                <!--Lấy từ db-->
                                <option value="Nữ" type="input">Nữ</option>
                                <option value="Nam" type="input">Nam</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Địa chỉ</label>
                            <input path="diaChiKH" id="diaChiKH" type="text" class="form-control" required="required" name="diaChiKH"></input>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input path="emailKH" id="emailKH" type="email" class="form-control" required="required" name="emailKH"></input>
                        </div>
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input path="soDienThoai" id="soDienThoai" type="tel" class="form-control" required="required" name="soDienThoai"></input>
                        </div>
                        <div class="form-group">
                            <label>Nghề nghiệp</label>
                            <input path="ngheNghiep" id="ngheNghiep" type="text" class="form-control" required="required" name="ngheNghiep"></input>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                        <input type="hidden" name="editCustomerId" id="editCustomerId">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="deleteCustomerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="customer_management/delete" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa khách hàng</h4>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa khách hàng này?</p>
                        <p class="text-warning"><small>Không thể khôi phục khi đã xóa</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận">  
                        <input type="hidden" name="deletecustomerId" id="deletecustomerId">
                        </input>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function deleteCustomer(element) {
        var id = element.parentNode.querySelector('#idCustomer').value;
        document.querySelector('#deletecustomerId').value = id;
    }
    function editCustomer(element) {
        // Lấy các phần tử con bên trong hàng của bảng
        var row = element.closest("tr");
        var cells = row.querySelectorAll("td");
        var name = row.cells[0].textContent;
        var birthday = row.cells[1].textContent;
        var gender = row.cells[2].textContent;
        var address = row.cells[3].textContent;
        var email = row.cells[4].textContent;
        var phone = row.cells[5].textContent;
        var cccd = row.cells[6].textContent;
        var job = row.cells[7].textContent;
        var id = element.parentNode.querySelector('#idCustomer').value;
                console.log(row, name, job, birthday, gender, address, cccd, email, phone, id);

        // Đưa dữ liệu vào các phần tử trong form sửa
        document.querySelector("#tenKhachHang").value = name;
        document.querySelector("#ngaySinhKH").value = birthday;
        document.querySelector("#diaChiKH").value = address;
        document.querySelector("#emailKH").value = email;
        document.querySelector("#soDienThoai").value = phone;
        document.querySelector("#ngheNghiep").value = job;
        document.querySelector("#editCustomerId").value = id;

        // Chọn giới tính
        var genderSelect = document.querySelector("select[name='gioiTinh']");
        if (gender == "0") {
            genderSelect.selectedIndex = 0;
        } else {
            genderSelect.selectedIndex = 1;
        }
    }
    window.onload = function () {
        $("tb").innerText = '';
    };

    $(document).ready(function () {
        setTimeout(function () {
            $("#tb").css("display", "none");
        }, 2000); // 2 seconds
    });
</script>
