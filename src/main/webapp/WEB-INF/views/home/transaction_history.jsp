<%-- 
    Document   : transaction_history
    Created on : Apr 2, 2023, 6:43:55 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>213435235236236</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Tên chủ tài khoản: </p><p>Tèo</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>Hoạt động cực mạnh</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p>Hôm qua</p></div></li>
            </ul>
        </div>
        <a href="<c:url value="/home/export_excel_file"/>"  class="mybuton-primary py-3 px-4">Xuất file excel</a>

    </div>

    <div>
        <form>
            <div class="mb-3">
                <label for="ngayHienThi" class="form-label">Chọn ngày hiển thị;</label>

                <div class="d-flex align-items-center">
                    <input type="date" class="form-control" id="ngayHienThi">
                    <input type="submit" class="mybuton-primary ml-4" value="Lọc"></input>
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
            <tr>               
                <td>1</td>
                <td>Chuyển khoản</td>
                <td>Tí</td>
                <td>1.000.000đ</td>
                <td>10/10/2023</td>
                <td>hello your friends</td>
                <td>123745934723947</td>
            </tr>
        </tbody>
    </table>



</div>
