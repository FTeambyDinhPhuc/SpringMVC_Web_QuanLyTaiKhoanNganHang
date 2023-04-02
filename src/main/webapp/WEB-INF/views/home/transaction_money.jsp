<%-- 
    Document   : transaction_money
    Created on : Apr 2, 2023, 3:42:52 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-lg-6">
            <form class="d-flex align-items-center" style="width: 450px">
                <input type="text" class="form-control my-4 mr-4" required="required" name="searchBankAccount"  placeholder="Nhập vào số tài khoản"></input>
                <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
            </form>
            <p class="warning-text">${messageTransactionMoney}</p>
            <p class="success-text">${messageSuccessTransactionMoney}</p>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>213435235236236</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p>10.000<span> VNĐ</span></p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>Hoạt động cực mạnh</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p>Hôm qua</p></div></li>
            </ul>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <div class="mb-3">
                    <label for="cccd" class="form-label">Nhập số căn cước công dân</label>
                    <input type="tel" class="form-control" id="cccd" placeholder="001082946357">
                </div>
                <div class="mb-3">
                    <label for="tienGiaoDich" class="form-label">Nhập số tiền muốn giao dịch</label>
                    <input type="number" class="form-control" id="tienGiaoDich" placeholder="100000">
                </div>
                <div class="d-flex justify-content-end">
                    <a href="#depositModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Nạp tiền</a>
                    <a href="#withdrawalModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Rút tiền</a>
                </div>
            </div>
        </div>
    </div>

    <div id="depositModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Nạp tiền vào tài khoản</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHang" class="form-label">Số tài khoản ngân hàng</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHang" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soCanCuoc" class="form-label">Số tài căn cước công dân</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHang" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienMuonNap" class="form-label">Số tiền muốn nạp</label>
                                <input type="number" class="form-control" id="soTienMuonNap" placeholder="10.000đ" readonly="true">
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

    <div id="withdrawalModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Rút tiền từ tài khoản</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHang" class="form-label">Số tài khoản ngân hàng</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHang" placeholder="001082946357" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soCanCuoc" class="form-label">Số tài căn cước công dân</label>
                                <input type="tel" class="form-control" id="soTaiKhoanNganHang" placeholder="001082946357" readonly="true">
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

