<%-- 
    Document   : transaction_money
    Created on : Apr 2, 2023, 3:42:52 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-lg-6">
            <form class="d-flex align-items-center" style="width: 450px">
                <input type="text" class="form-control my-4 mr-4" name="searchSTK" required="required"  placeholder="Nhập vào số tài khoản"></input>
                <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
            </form>
            <p class="warning-text">${messageTransactionMoney}</p>
            <p class="success-text">${messageSuccessTransactionMoney}</p>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${acbank.getSoTaiKhoanNganHang()}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p><fmt:formatNumber value="${acbank.getSoDuTaiKhoan()}" pattern="###,### VNĐ"/></p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${acbank.getTrangThaiTaiKhoan()==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${acbank.getNgayMoTaiKhoan()}" pattern="dd/MM/yyyy"/></p></div></li>
                <input type="hidden" id="idacbank" value="${acbank.getSoTaiKhoanNganHang()}">
            </ul>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <div class="mb-3">
                    <label for="cccd" class="form-label">Nhập số căn cước công dân</label>
                    <input type="text" class="form-control" id="cccd">
                </div>
                <div class="mb-3">
                    <label for="tienGiaoDich" class="form-label">Nhập số tiền muốn giao dịch</label>
                    <input type="number" class="form-control" id="tienGiaoDich">
                </div>
                <div class="d-flex justify-content-end">
                    <a onclick="naptien(this)" href="#depositModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Nạp tiền</a>
                    <a href="#withdrawalModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Rút tiền</a>                   
                </div>
            </div>
        </div>
    </div>

    <div id="depositModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="depositModal/add" modelAttribute="naptien" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Nạp tiền vào tài khoản</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHang" class="form-label">Số tài khoản ngân hàng</label>
                                <input name="soTaiKhoanNganHang" value="${acbank.getSoTaiKhoanNganHang()}" type="tel" class="form-control" id="soTaiKhoanNganHang"  readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soCanCuoc" class="form-label">Số căn cước công dân</label>
                                <input name="canCuoc" id="canCuoc" type="text" class="form-control" id="canCuoc"  readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienMuonNap" class="form-label">Số tiền muốn nạp</label>
                                <input name="soTienMuonNap" id="soTienMuonNap" type="number" class="form-control" id="soTienMuonNap"   readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="phiGiaoDich" class="form-label">Phí giao dịch</label>
                                <input name="phiGiaoDich" id="phiGiaoDich" type="number" class="form-control" id="phiGiaoDich" value="2000" readonly="true">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                        <input type="hidden" id="idnhanvien" name="idnhanvien" value="${sessionScope.idNhanVien}" id="idnhanvien">
                        <input type="hidden" id="sotienhientai" name="sotienhientai" value="${acbank.getSoDuTaiKhoan()}" id="sotienhientai">
                        <input type="hidden" id="cancuockiemtra" name="cancuockiemtra" value="${acbank.getSoDuTaiKhoan()}" id="cancuockiemtra">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="withdrawalModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form >
                    <div class="modal-header">
                        <h4 class="modal-title">Rút tiền từ tài khoản</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHang" class="form-label">Số tài khoản ngân hàng</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHangrut" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soCanCuoc" class="form-label">Số căn cước công dân</label>
                                <input type="tel" class="form-control" id="Cancuoc" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienMuonRut" class="form-label">Số tiền muốn rút</label>
                                <input type="number" class="form-control" id="soTienMuonRut" placeholder="10.000đ" readonly="true">
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
<script>
    function naptien(element) {
        var cccd = document.getElementById('cccd').value;
        var tienGiaoDich = document.getElementById('tienGiaoDich').value;
        document.getElementById('canCuoc').value = cccd;
        document.getElementById('soTienMuonNap').value = tienGiaoDich;
        console.log(cccd);
    }
</script>

