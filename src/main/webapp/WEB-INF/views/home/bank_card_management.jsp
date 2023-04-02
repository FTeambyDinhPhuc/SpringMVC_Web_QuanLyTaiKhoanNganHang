<%-- 
    Document   : bank_card_management
    Created on : Mar 15, 2023, 3:16:33 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-lg-6">
            <form class="d-flex align-items-center" style="width: 450px">
                <input type="text" class="form-control my-4 mr-4" required="required" name="searchBankAccount"  placeholder="Nhập vào số tài khoản"></input>
                <input type="submit" class="mybuton-primary" value="Tìm kiếm"></input>
            </form>
            <p class="warning-text">${messageBankCard}</p>
            <p class="success-text">${messageSuccessBankCard}</p>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số tài khoản: </p><p>213435235236236</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số dư tài khoản: </p><p>10.000<span> VNĐ</span></p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Trạng thái tài khoản: </p><p>Hoạt động cực mạnh</p></div></li>
                <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày mở tài khoản: </p><p>Hôm qua</p></div></li>
            </ul>
            <a href="#createBankCard" data-toggle="modal" class="btn btn-outline-success my-5" style="font-size: 16px">Mở thẻ ngân hàng</a>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <div class="card text-white bg-secondary mb-3">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <p class="m-0">3124798127491264</p>
                        <div>
                            <a href="#extendBankCardModal" data-toggle="modal" class="mybuton-icon-extend px-3"><i class="fa-regular fa-calendar-plus " data-toggle="tooltip" title="Gia hạn thẻ"></i></a>
                            <a href="#unlockBankCardModal" data-toggle="modal" class="mybuton-icon-unlock px-3"><i class="fa-solid fa-lock-open " data-toggle="tooltip" title="Mở khóa thẻ"></i></a>
                            <a href="#lockBankCardModal" data-toggle="modal" class="mybuton-icon-lock px-3"><i class="fa-solid fa-lock " data-toggle="tooltip" title="Khóa thẻ"></i></a>
                        </div>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Ngày cấp</h5>
                        <p class="card-text">12/2/2000</p>
                        <h5 class="card-title">Ngày hết hạn</h5>
                        <p class="card-text">12/9/2005</p>
                        <h5 class="card-title">CVV</h5>
                        <p class="card-text">234</p>
                        <h5 class="card-title">Trạng thái thẻ</h5>
                        <p class="card-text">Hoạt động</p>
                    </div>
                </div>


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

