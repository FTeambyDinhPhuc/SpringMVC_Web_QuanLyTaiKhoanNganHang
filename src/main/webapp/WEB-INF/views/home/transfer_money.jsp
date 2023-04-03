<%-- 
    Document   : transfer_money
    Created on : Apr 2, 2023, 3:44:05 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-lg-6">
            <div class="table-outline">
                <form class="d-flex align-items-center" style="width: 450px">
                    <input type="text" class="form-control my-4 mr-4" required="required" name="searchBankAccount" value="${param.searchBankAccount}" placeholder="Nhập vào số tài khoản chuyển"></input>
                    <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                </form>
                <p class="warning-text">${messageTransferMoney}</p>
                <p class="success-text">${messageSuccessTransferMoney}</p>

                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${sessionScope.soTaiKhoan}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p><fmt:formatNumber value="${sessionScope.soDuTaiKhoan}" pattern="###,### VNĐ"/></p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${sessionScope.trangThaiTaiKhoan==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${sessionScope.ngayMoTaiKhoan}" pattern="dd/MM/yyyy"/></p></div></li>
                    <input type="hidden" id="idacbank" value="${acbank.getSoTaiKhoanNganHang()}">
                </ul>


            </div>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <form class="d-flex align-items-center" style="width: 450px">
                    <input type="text" class="form-control my-4 mr-4" required="required" name="searchBankAccount1"  value="${param.searchBankAccount1}" placeholder="Nhập vào số tài khoản nhận"></input>
                    <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                </form>

                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${acbank1.getSoTaiKhoanNganHang()}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p><fmt:formatNumber value="${acbank1.getSoDuTaiKhoan()}" pattern="###,### VNĐ"/></p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${acbank1.getTrangThaiTaiKhoan()==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${acbank1.getNgayMoTaiKhoan()}" pattern="dd/MM/yyyy"/></p></div></li>
                    <input type="hidden" id="idacbank1" value="${acbank1.getSoTaiKhoanNganHang()}">
                </ul>


            </div>
        </div>
    </div>
    <p class="warning-text mt-4">${messageTransferMoney}</p>
    <p class="success-text mt-4">${messageSuccessTransferMoney}</p>
    <div class="table-outline">
        <form>
            <div class="mb-3">
                <label for="cccd" class="form-label">Nhập số căn cước công dân</label>
                <input type="tel" class="form-control" id="cccd" placeholder="001082946357">
            </div>
            <div class="mb-3">
                <label for="tienGiaoDich" class="form-label">Nhập số tiền muốn giao dịch</label>
                <input type="number" class="form-control" id="tienGiaoDich" placeholder="100000">
            </div>
            <div class="mb-3">
                <label for="noiDungChuyenkhoan" class="form-label">Nội dung chuyển khoản</label>
                <input type="text" class="form-control" id="noiDungChuyenkhoan" placeholder="nội dung chuyển khoản">
            </div>
            <div class="d-flex justify-content-end">
                <input type="submit" class="mybuton-outline mr-4" data-dismiss="modal" value="Hủy"></input>
                <a href="#transferMoneyModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Chuyển tiền</a>
            </div>
        </form>
    </div>

    <div id="transferMoneyModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Chuyển tiền</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHangGui" class="form-label">Số tài khoản người gửi</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHangGui" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="tenNguoiGui" class="form-label">Tên người gửi</label>
                                <input type="text" class="form-control" id="tenNguoiGui" placeholder="Nguyễn Văn A" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHangNhan" class="form-label">Số tài khoản người nhận</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHangNhan" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="tenNguoiNhan" class="form-label">Tên người nhận</label>
                                <input type="text" class="form-control" id="tenNguoiNhan" placeholder="Nguyễn Văn A" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="noiDungChuyenKhoan" class="form-label">Nội dung chuyển khoản</label>
                                <input type="text" class="form-control" id="noiDungChuyenKhoan" placeholder="nội dung gì đó" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienGiaoDich" class="form-label">Số tiền giao dịch</label>
                                <input type="number" class="form-control" id="soTienGiaoDich" placeholder="10.000đ" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="phiGiaoDich" class="form-label">Phí giao dịch</label>
                                <input type="number" class="form-control" id="phiGiaoDich" placeholder="2.000đ" readonly="true">
                            </div>

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
</div>
