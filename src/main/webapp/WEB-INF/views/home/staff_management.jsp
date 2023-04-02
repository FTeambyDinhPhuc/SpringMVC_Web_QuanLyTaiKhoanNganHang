<%-- 
    Document   : staff_management
    Created on : Mar 14, 2023, 12:09:25 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="d-flex justify-content-between align-items-center">
        <form class="d-flex align-items-center" style="width: 450px">
            <input type="text" class="form-control my-4 mr-4" name="searchstaff" autocomplete="off"  placeholder="Nhập vào tên nhân viên"></input>
            <button type="submit"  class="mybuton-primary">Tìm kiếm</button>
        </form>
        <a href="<c:url value="/auth/register"/>" class="btn btn-outline-success" style="font-size: 16px">Thêm nhân viên</a>
    </div>
    <p class="warning-text">${messageStaff}</h3>
    <p class="success-text">${messageSuccessStaff}</p>
    
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Tên nhân viên</th>
                <th scope="col">Chức vụ</th>
                <th scope="col">Ngày sinh</th>
                <th scope="col">Giới tính</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">Căn cước</th>
                <th scope="col">Email</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Trạng thái tài khoản</th>
                <th scope="col">Hành động</th>
            </tr>
        </thead>
        <tbody >
            <c:forEach var="user" items="${nhanviens}">
                <tr>               
                    <td>${user.getTenNhanVien()}</td>
                    <td>${user.chucVu.getTenChucVu()}</td>
                    <td>${user.getNamSinh()}</td>
                    <td>${user.getGioiTinh()==0 ? "Nữ" : "Nam"}</td>
                    <td>${user.getDiaChi()}</td>
                    <td>${user.getCccd()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getSoDienThoai()}</td>
                    <td>${user.getTrangThaiTaiKhoan()==0?"Khóa":"Đang hoạt động"}</td>
                    <td>
                        <a onclick="editStaff(this)" id="btnEditStaff" href="#editStaffModal" data-toggle="modal" class="mybuton-icon-edit px-3"><i class="fa-solid fa-pen-to-square icon-edit" data-toggle="tooltip" title="Edit"></i></a>
                        <a onclick="deleteStaff(this)" id="btnDeleteStaff" href="#deleteStaffModal" data-toggle="modal" class="mybuton-icon-delete px-3"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>
                        <input type="hidden" id="idStaff" value="${user.getId()}">
                        <input type="hidden" id="chucVuTd" value="${user.chucVu.getTenChucVu()}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <div id="editStaffModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" modelAttribute="staff" action="staff_management/edit">
                    <div class="modal-header">
                        <h4 class="modal-title">Sửa nhân viên</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên nhân viên</label>
                            <input path="tenNhanVien" id="tenNhanVien" type="text" class="form-control" required="required" name="tenNhanVien"></input>
                        </div>
                        <div class="form-group">
                            <label>Chức vụ</label>

                            <select path="chucVu" class="form-control"required="required" autocomplete="off" name="chucVu" >
                                <option value="2" type="input" >Nhân viên</option>
                                <option value="1" type="input">Quản lý</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input path="namSinh" id="namSinh" type="date" class="form-control" required="required" name="namSinh"></input>
                        </div>
                        <div class="form-group">
                            <label>Giới tính</label>
                            <select path="gioiTinh" class="form-control"required="required" autocomplete="off" name="gioiTinh" >
                                <option value="1" type="input">Nam</option>
                                <option value="0" type="input">Nữ</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Địa chỉ</label>
                            <input path="diaChi" id="diaChi" type="text" class="form-control" required="required" name="diaChi"></input>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input path="email" id="email" type="email" class="form-control" required="required" name="email"></input>
                        </div>
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input path="soDienThoai" id="soDienThoai" type="tel" class="form-control" required="required" name="soDienThoai"></input>
                        </div>
                        <div class="form-group">
                            <label>Trạng thái tài khoản</label>
                            <select path="trangThaiTaiKhoan" class="form-control"required="required" autocomplete="off" name="trangThaiTaiKhoan" >
                                <option value="1" type="input">Hoạt động</option>
                                <option value="0" type="input">Tạm dừng</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                        <input type="hidden" name="editStaffId" id="editStaffId">
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div id="deleteStaffModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" action="staff_management/delete">
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa nhân viên</h4>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa nhân viên này?</p>
                        <p class="text-warning"><small>Không thể khôi phục khi đã xóa</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                        <input type="hidden" name="deleteStaffId" id="deleteStaffId">
                    </div>
                </form>
            </div>
        </div>
    </div> 
</div>
<script>
    function deleteStaff(element) {
        var id = element.parentNode.querySelector('#idStaff').value;
        document.querySelector('#deleteStaffId').value = id;
    }
    function editStaff(element) {
        // Lấy các phần tử con bên trong hàng của bảng
        var row = element.closest("tr");
        var cells = row.querySelectorAll("td");
        var name = row.cells[0].textContent;
        var position = row.cells[1].textContent;
        var birthday = row.cells[2].textContent;
        var gender = row.cells[3].textContent;
        var address = row.cells[4].textContent;
        var cccd = row.cells[5].textContent;
        var email = row.cells[6].textContent;
        var phone = row.cells[7].textContent;
        var status = row.cells[8].textContent;
        var id = row.cells[9].querySelector("#idStaff").value;
        console.log(row, name, position, birthday, gender, address, cccd, email, phone, status, id);
        // Đưa dữ liệu vào các phần tử trong form sửa
        document.querySelector("#tenNhanVien").value = name;
        document.querySelector("#chucVuTd").value = position;
        document.querySelector("#namSinh").value = birthday;
        document.querySelector("#diaChi").value = address;
        document.querySelector("#email").value = email;
        document.querySelector("#soDienThoai").value = phone;
        document.querySelector("#editStaffId").value = id;

        // Chọn giới tính
        var genderSelect = document.querySelector("select[name='gioiTinh']");
        if (gender == "Nam") {
            genderSelect.selectedIndex = 0;
        } else {
            genderSelect.selectedIndex = 1;
        }
        var cvSelect = document.querySelector("select[name='chucVu']");
        if (position == "Nhân viên ngân hàng") {
            cvSelect.selectedIndex = 0;
        } else {
            cvSelect.selectedIndex = 1;
        }
        // Chọn trạng thái tài khoản
        var statusSelect = document.querySelector("select[name='trangThaiTaiKhoan']");
        if (status == "Đang hoạt động") {
            statusSelect.selectedIndex = 0;
        } else {
            statusSelect.selectedIndex = 1;
        }
    }
</script>
