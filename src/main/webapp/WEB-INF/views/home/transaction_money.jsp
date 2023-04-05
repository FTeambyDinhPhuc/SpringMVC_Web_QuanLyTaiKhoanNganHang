<%-- 
    Document   : transaction_money
    Created on : Apr 2, 2023, 3:42:52 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${sessionScope.soTaiKhoan9}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Tên khách hàng: </p><p>${sessionScope.tenKhachHang9}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p><fmt:formatNumber value="${sessionScope.soDuTaiKhoan9}" pattern="###,### VNĐ"/></p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${sessionScope.trangThaiTaiKhoan9==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${sessionScope.ngayMoTaiKhoan9}" pattern="dd/MM/yyyy"/></p></div></li>
                <input type="hidden" id="idacbank1" value="${acbank1.getSoTaiKhoanNganHang()}">

            </ul>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <div class="mb-3">
                    <label for="cccd" class="form-label">Nhập số căn cước công dân</label>
                    <input type="number" class="form-control" id="cccd" name="cccd">
                </div>
                <div class="mb-3">
                    <label for="tienGiaoDich" class="form-label">Số tiền muốn nạp</label>
                    <input type="number" class="form-control" id="tienGiaoDich" name="tienGiaoDich">
                </div>
                <div class="d-flex justify-content-end">
                    <a onclick="naptien(this)" href="#depositModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Nạp tiền</a>
                    <a onclick="ruttien(this)" href="#withdrawalModal" data-toggle="modal" class="mybuton-primary py-3 px-4">Rút tiền</a>     
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
                                <input name="soTaiKhoanNganHang" value="${sessionScope.soTaiKhoan9}" type="tel" class="form-control" id="soTaiKhoanNganHang"  readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="canCuoc" class="form-label">Nhập số căn cước công dân</label>
                                <input type="number" class="form-control" id="canCuoc" name="canCuoc"  readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienMuonNap" class="form-label">Số tiền muốn nạp</label>
                                <input name="soTienMuonNap" id="soTienMuonNap" type="number" class="form-control" id="soTienMuonNap"   readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="phiGiaoDich" class="form-label">Phí giao dịch</label>
                                <input name="phiGiaoDich" id="phiGiaoDich" type="number" class="form-control" readonly="true">
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
                <form  action="depositModal/rut" modelAttribute="ruttien" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Rút tiền từ tài khoản</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="mb-3">
                                <label for="soTaiKhoanNganHang" class="form-label">Số tài khoản ngân hàng</label>
                                <input type="tel" class="form-control" value="${sessionScope.soTaiKhoan9}" id="soTaiKhoanNganHangrut"  name="soTaiKhoanNganHangrut" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="canCuoc" class="form-label">Số căn cước công dân</label>
                                <input type="number" class="form-control" id="canCuoc1" name="canCuoc1"  readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="soTienMuonRut1" class="form-label">Số tiền muốn rút</label>
                                <input type="number" class="form-control" name="soTienMuonRut1" id="soTienMuonRut1" readonly="true">
                            </div>
                            <div class="mb-3">
                                <label for="phiGiaoDich1" class="form-label">Phí giao dịch</label>
                                <input type="number" class="form-control" name="phiGiaoDich1" id="phiGiaoDich1"readonly="true">
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                        <input type="hidden" id="sotienhientairut" name="sotienhientairut" value="${acbank.getSoDuTaiKhoan()}" id="sotienhientai">
                        <input type="hidden" id="idnhanvienrut" name="idnhanvienrut" value="${sessionScope.idNhanVien}" id="idnhanvien">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function naptien(element) {
        var cccd1 = document.getElementById('cccd').value;
        var tienGiaoDich1 = document.getElementById('tienGiaoDich').value;
        // Kiểm tra các giá trị đầu vào
        if (cccd1.trim() == '' || tienGiaoDich1.trim() == '') {
            alert('Vui lòng nhập đầy đủ thông tin chuyển khoản');
            return window.location.reload();
        }
        var phiGiaoDich1 = 0;
        tienGiaoDich1 = parseFloat(tienGiaoDich1); // chuyển đổi sang kiểu số
        if (tienGiaoDich1 < 10000000) {
            phiGiaoDich1 = 10500;
        } else if (tienGiaoDich1 >= 10000000 && tienGiaoDich1 < 50000000) {
            phiGiaoDich1 = 14000 + tienGiaoDich1 * 0.0028;
        } else if (tienGiaoDich1 < 100000000 && tienGiaoDich1 >= 50000000) {
            phiGiaoDich1 = 28000 + tienGiaoDich1 * 0.0028;
        } else if (tienGiaoDich1 < 500000000 && tienGiaoDich1 >= 100000000) {
            phiGiaoDich1 = 36000 + phiGiaoDich1 * 0.0028;
        } else if (tienGiaoDich1 === 500000000) {
            phiGiaoDich1 = 140000 + tienGiaoDich1 * 0.0028;
        } else {
            alert('Số tiền chuyển 1 lần không vượt quá 500.000.000');
            return window.location.reload();
        }
        document.getElementById('canCuoc').value = cccd1;
        document.getElementById('soTienMuonNap').value = tienGiaoDich1;
        document.getElementById('phiGiaoDich').value = phiGiaoDich1;
    }

</script>
<script>
    function ruttien(element) {
        var cccd = document.getElementById('cccd').value;
        var tienGiaoDich = document.getElementById('tienGiaoDich').value;
        // Kiểm tra các giá trị đầu vào
        if (cccd.trim() == '' || tienGiaoDich.trim() == '') {
            alert('Vui lòng nhập đầy đủ thông tin chuyển khoản');
            return window.location.reload();
        }
        var phiGiaoDich = 0;
        tienGiaoDich = parseFloat(tienGiaoDich); // chuyển đổi sang kiểu số
        if (tienGiaoDich < 10000000) {
            phiGiaoDich = 10500;
        } else if (tienGiaoDich >= 10000000 && tienGiaoDich < 50000000) {
            phiGiaoDich = 14000 + tienGiaoDich * 0.0028;
        } else if (tienGiaoDich < 100000000 && tienGiaoDich >= 50000000) {
            phiGiaoDich = 28000 + tienGiaoDich * 0.0028;
        } else if (tienGiaoDich < 500000000 && tienGiaoDich >= 100000000) {
            phiGiaoDich = 36000 + tienGiaoDich * 0.0028;
        } else if (tienGiaoDich === 500000000) {
            phiGiaoDich = 140000 + tienGiaoDich * 0.0028;
        } else {
            alert('Số tiền chuyển 1 lần không vượt quá 500.000.000');
            return window.location.reload();
        }
        document.getElementById('canCuoc1').value = cccd;
        document.getElementById('soTienMuonRut1').value = tienGiaoDich;
        document.getElementById('phiGiaoDich1').value = phiGiaoDich;
    }

</script>

<script>
    <c:set var="soTaiKhoan9" value="${sessionScope.soTaiKhoan9}" />
    <c:set var="tenKhachHang9" value="${sessionScope.tenKhachHang9}" />
    <c:set var="soDuTaiKhoan9" value="${sessionScope.soDuTaiKhoan9}" />
    <c:set var="trangThaiTaiKhoan9" value="${sessionScope.trangThaiTaiKhoan9}" />
    <c:set var="ngayMoTaiKhoan9" value="${sessionScope.ngayMoTaiKhoan9}" />
    var button = document.getElementById("myButton");
    button.addEventListener("click", function () {
        // Xóa các giá trị trong sessionStorage
        var soTaiKhoan9 = "${soTaiKhoan9}";
        sessionStorage.removeItem(soTaiKhoan9);

        var tenKhachHang9 = "${tenKhachHang9}";
        sessionStorage.removeItem(tenKhachHang9);

        var soDuTaiKhoan9 = "${soDuTaiKhoan9}";
        sessionStorage.removeItem(soDuTaiKhoan9);

        var trangThaiTaiKhoan9 = "${trangThaiTaiKhoan9}";
        sessionStorage.removeItem(trangThaiTaiKhoan9);

        var ngayMoTaiKhoan9 = "${ngayMoTaiKhoan9}";
        sessionStorage.removeItem(ngayMoTaiKhoan9);
        // Tải lại trang web
        window.location.reload();
    });
</script>

