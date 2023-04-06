<%-- 
    Document   : transaction_history
    Created on : Apr 2, 2023, 6:43:55 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="d-flex justify-content-between align-items-end">
        <div class="col-lg-6 table-outline">
            <form class="d-flex align-items-center" style="width: 450px">
                <input type="text" class="form-control my-4 mr-4" required="required" name="searchBankAccount"  placeholder="Nhập vào số tài khoản"></input>
                <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                
            </form>
            <p class="warning-text">${messageTransactionMoney}</p>
            <p class="success-text">${messageSuccessTransactionMoney}</p>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${ttthe.getSoTaiKhoanNganHang()}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Tên chủ tài khoản: </p><p>${khang.getTenKhachHang()}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${ttthe.getTrangThaiTaiKhoan()==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${ttthe.getNgayMoTaiKhoan()}" pattern="dd/MM/yyyy"/></p></div></li>
            </ul>
        </div>
            <form action="transaction_history/xuatexcel" method="post">
                <a type="submit"  class="mybuton-primary py-3 px-4">Xuất file excel</a>
                <input type="hidden" name="sotk" value="${ttthe.getSoTaiKhoanNganHang()}">
            </form>
        

    </div>

    <div>
        <form action="transaction_history/loc" method="post" >
            <div class="mb-3">
                <label for="ngayHienThi" class="form-label">Chọn ngày hiển thị;</label>

                <div class="d-flex align-items-center">
                    <input type="date" class="form-control" id="ngayHienThi" name="loc">
                    <input type="submit" class="mybuton-primary ml-4" value="Lọc"></input>
                    <input type="hidden" name="idkhach" value="${ttthe.getSoTaiKhoanNganHang()}">
                </div>
            </div>
        </form>
    </div>
    <p class="warning-text">${messageTransactionHistory}</h3>
    <p class="success-text">${messageSuccessTransactionHistory}</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Mã giao dịch</th>
                <th scope="col">Loại giao dịch</th>
                <th scope="col">Nhân viên lập</th>
                <th scope="col">Số tiền giao dịch</th>
                <th scope="col">Thời gian giao dịch</th>
                <th scope="col">Nội dung giao dịch</th>
                <th scope="col">Tài khoản đích</th>
            </tr>
        </thead>
        <tbody >
            <c:forEach var="saoke" items="${saoke}">
                <tr>               
                    <td>${saoke.getId()}</td>
                    <td>Chuyển khoản</td>
                    <td>Tí</td>
                    <td>1.000.000đ</td>
                    <td>10/10/2023</td>
                    <td>hello your friends</td>
                    <td>123745934723947</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



</div>
