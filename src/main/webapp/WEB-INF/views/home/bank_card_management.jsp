<%-- 
    Document   : bank_card_management
    Created on : Mar 15, 2023, 3:16:33 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-lg-6">
            <form class="d-flex align-items-center" style="width: 450px">
                <input type="text" class="form-control my-4 mr-4" name="searchAccount"autocomplete="off" required="required" name="searchBankAccount"  placeholder="Nhập vào số tài khoản"></input>
                <input type="submit" class="mybuton-primary" value="Tìm kiếm"></input>
            </form>
            <p class="warning-text">${messageBankCard}</p>
            <p class="success-text">${messageSuccessBankCard}</p>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>${accountBank.getSoTaiKhoanNganHang()}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p><fmt:formatNumber value="${accountBank.getSoDuTaiKhoan()}" pattern="###,### VNĐ"/></p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>${accountBank.getTrangThaiTaiKhoan()==0 ? "Khóa" : "Đang hoạt động"}</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p><fmt:formatDate value="${accountBank.getNgayMoTaiKhoan()}" pattern="dd/MM/yyyy"/></p></div></li>
            </ul>
            <a href="#createBankCard" data-toggle="modal" class="btn btn-outline-success my-5" style="font-size: 16px">Mở thẻ ngân hàng</a>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <c:forEach var="the" items="${theBank}">
                    <div class="card text-white bg-secondary mb-3">
                        <div class="card-header d-flex align-items-center justify-content-between">
                            <p class="m-0">${the.getSoThe()}</p>
                            <div>    
                                <c:choose>
                                    <c:when test="${the.getTrangThaiThe() eq 1}">
                                        <a href="#extendBankCardModal" data-toggle="modal" class="mybuton-icon-extend px-3"><i class="fa-regular fa-calendar-plus " data-toggle="tooltip" title="Gia hạn thẻ"></i></a>                                       
                                        <a href="#lockBankCardModal" data-toggle="modal" class="mybuton-icon-lock px-3"><i class="fa-solid fa-lock " data-toggle="tooltip" title="Khóa thẻ"></i></a>

                                    </c:when>
                                    <c:otherwise>
                                        <a href="#extendBankCardModal" data-toggle="modal" class="mybuton-icon-extend px-3"><i class="fa-regular fa-calendar-plus " data-toggle="tooltip" title="Gia hạn thẻ"></i></a>
                                        <a href="#unlockBankCardModal" data-toggle="modal" class="mybuton-icon-unlock px-3"><i class="fa-solid fa-lock-open " data-toggle="tooltip" title="Mở khóa thẻ"></i></a>
                                        </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Ngày cấp</h5>
                            <p class="card-text"><fmt:formatDate value="${the.getNgayCapThe()}" pattern="dd/MM/yyyy"/></p>
                            <h5 class="card-title">Ngày hết hạn</h5>
                            <p class="card-text"><fmt:formatDate value="${the.getNgayHetHan()}" pattern="dd/MM/yyyy"/></p>
                            <h5 class="card-title">CVV</h5>
                            <p class="card-text">${the.getCvv()}</p>
                            <h5 class="card-title">Trạng thái thẻ</h5>
                            <p class="card-text">${the.getTrangThaiThe()==0 ? "Khóa" : "Đang hoạt động"}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <div id="createBankCard" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Mở thẻ ngân hàng</h4>

                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Chọn loại thẻ</label>
                            <select class="form-control"required="required" name="cardtype" >
                                <!--Lấy từ db-->
                                <option>Thẻ ghi nợ nội địa</option>
                                <option>Thẻ ghi nợ quốc tế</option>
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

    <div id="extendBankCardModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Gia hạn thẻ ngân hàng</h4>

                    </div>
                    <div class="modal-body">
                        <p>Xác nhận gia hạn thẻ ngân hàng?</p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="unlockBankCardModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Mở khóa thẻ ngân hàng</h4>

                    </div>
                    <div class="modal-body">
                        <p>Xác nhận mở khóa thẻ ngân hàng?</p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="mybuton-outline" data-dismiss="modal" value="Hủy"></input>
                        <input type="submit" class="mybuton-primary" value="Xác nhận"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="lockBankCardModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Khóa thẻ ngân hàng</h4>

                    </div>
                    <div class="modal-body">
                        <p>Xác nhận khóa thẻ ngân hàng?</p>
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

