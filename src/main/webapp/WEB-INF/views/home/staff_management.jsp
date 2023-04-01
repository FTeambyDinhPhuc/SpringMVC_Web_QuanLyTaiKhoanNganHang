<%-- 
    Document   : staff_management
    Created on : Mar 14, 2023, 12:09:25 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid px-4">
    <h1 class="my-4">Quản lý nhân viên</h1>
    <div class="d-flex justify-content-between align-items-center">
        <form class="d-flex align-items-center" style="width: 450px">
            <input type="text" class="form-control my-4 mr-4" name="searchstaff" autocomplete="off"  placeholder="Nhập vào tên nhân viên"></input>
            <button type="submit"  class="mybuton-primary">Tìm kiếm</button>
        </form>
        <a href="<c:url value="/auth/register"/>" class="btn btn-outline-success" style="font-size: 16px">Thêm nhân viên</a>
    </div>
    <p class="warning-text">${messageStaff}</h3>
    
    
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
                        <a href="#editStaffModal" data-toggle="modal" class="mybuton-icon-edit px-3"><i class="fa-solid fa-pen-to-square icon-edit" data-toggle="tooltip" title="Edit"></i></a>
                        <a  onclick="deleteStaff(this)" id="btnDeleteStaff" href="#deleteStaffModal" data-toggle="modal" class="mybuton-icon-delete px-3"><i class="fa-sharp fa-solid fa-trash" data-toggle="tooltip" title="Delete"></i></a>
                        <input type="hidden" id="idStaff" value="${user.getId()}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    
    <div id="editStaffModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Sửa nhân viên</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên nhân viên</label>
                            <input type="text" class="form-control" required="required" name="fullname"></input>
                        </div>
                        <div class="form-group">
                            <label>Chức vụ</label>

                            <select class="form-control"required="required" name="position" >
                                <option>Nhân viên</option>
                                <option>Quản lý</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <input type="date" class="form-control" required="required" name="birthday"></input>
                        </div>
                        <div class="form-group">
                            <label>Giới tính</label>
                            <select class="form-control"required="required" name="sex" >
                                Lấy từ db
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
                            <label>Trạng thái tài khoản</label>
                            <select class="form-control"required="required" name="status" >
                                Lấy từ db
                                <option>Hoạt động</option>
                                <option>Tạm dừng</option>
                            </select>
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
</script>
