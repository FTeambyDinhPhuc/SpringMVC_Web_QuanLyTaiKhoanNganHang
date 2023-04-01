<%-- 
    Document   : customer_detail
    Created on : Mar 15, 2023, 12:24:01 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid px-4">
    <h1 class="my-4">Thông tin khách hàng</h1>
    <div class="row">
        <div class="col-lg-6">
            <div class="table-outline">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Tên khách hàng: </p><p>Tèo</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Ngày sinh: </p><p>13/02/2000</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Giới tính: </p><p>Nam</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Địa chỉ: </p><p>Hồ Chí Minh</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Căn cước công dân: </p><p>123524756</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Email: </p><p>a@gmail.com</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Số điện thoại: </p><p>039912123</p></div></li>
                    <li class="list-group-item"><div class="d-flex justify-content-between align-items-center"><p>Nghề nghiệp: </p><p>Sinh viên</p></div></li>

                </ul>
            </div>
            <a href="#createBankAccount" data-toggle="modal" class="btn btn-outline-success my-5" style="font-size: 16px">Mở tài khoản</a>
        </div>
        <div class="col-lg-6">
            <div class="table-outline">
                <div class="card border-dark mb-3" >
                    <div class="card-header">Tài khoản 1</div>
                    <div class="card-body text-dark">
                        <h5 class="card-title">Số dự tài khoản</h5>
                        <p class="card-text">10.000<span> VNĐ</span></p>
                        <h5 class="card-title">Trạng thái tài khoản</h5>
                        <p class="card-text">Đang hoạt động</p>
                        <h5 class="card-title">Ngày mở tài khoản</h5>
                        <p class="card-text">1/1/1999</p>
                        <h5 class="card-title">Ngày đóng tài khoản</h5>
                        <p class="card-text">2/2/2222</p>
                    </div>
                </div>

                <div class="card border-dark mb-3" >
                    <div class="card-header">Tài khoản 2</div>
                    <div class="card-body text-dark">
                        <h5 class="card-title">Số dự tài khoản</h5>
                        <p class="card-text">10.000<span> VNĐ</span></p>
                        <h5 class="card-title">Trạng thái tài khoản</h5>
                        <p class="card-text">Đang hoạt động</p>
                        <h5 class="card-title">Ngày mở tài khoản</h5>
                        <p class="card-text">1/1/1999</p>
                        <h5 class="card-title">Ngày đóng tài khoản</h5>
                        <p class="card-text">2/2/2222</p>
                    </div>
                </div>


            </div>
        </div>
    </div>

    <div id="createBankAccount" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Mở tài khoản ngân hàng</h4>

                    </div>
                    <div class="modal-body">
                        <p>Xác nhận mở tài khoản ngân hàng?</p>
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


