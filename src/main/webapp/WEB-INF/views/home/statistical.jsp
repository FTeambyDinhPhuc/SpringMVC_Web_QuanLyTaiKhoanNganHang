<%-- 
    Document   : statistical
    Created on : Apr 2, 2023, 5:15:47 PM
    Author     : dinhp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid px-4">
    <h1 class="my-4">${pageTitle}</h1>
    <div class="row">
        <div class="col-xl-4 col-md-6">
            <div class="card bg-primary text-white mb-4">
                <div class="card-body">Tổng nhân viên: <span>10000</span></div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <a class="small text-white stretched-link" href="#">View Details</a>
                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
        </div>
        <div class="col-xl-4 col-md-6">
            <div class="card bg-warning text-white mb-4">
                <div class="card-body">Tổng khách hàng: <span>10000</span></div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <a class="small text-white stretched-link" href="#">View Details</a>
                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
        </div>
        <div class="col-xl-4 col-md-6">
            <div class="card bg-success text-white mb-4">
                <div class="card-body">Tổng số thẻ đã phát hành: <span>2000</span></div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <a class="small text-white stretched-link" href="#">View Details</a>
                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
        </div>
        <div class="col-xl-4 col-md-6">
            <div class="card bg-info text-white mb-4">
                <div class="card-body">Tổng tiền trong ngân hàng: <span>2000</span></div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <a class="small text-white stretched-link" href="#">View Details</a>
                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                </div>
            </div>
        </div>

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
    <p class="warning-text">${messageStatistical}</h3>
    <p class="success-text">${messageSuccessStatistical}</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Ngày</th>
                <th scope="col">Tiền nạp</th>
                <th scope="col">Tiền rút </th>
                <th scope="col">Phí giao dịch</th>
                <th scope="col">Số lượng giao dịch</th>
            </tr>
        </thead>
        <tbody >
            <tr>               
                <td>10-10-2022</td>
                <td>7.000.000.000đ</td>
                <td>1.000.000.000đ</td>
                <td>100.000.000đ</td>
                <td>1000</td>

            </tr>
            <tr>               
                <td>11-10-2022</td>
                <td>7.000.000.000đ</td>
                <td>1.000.000.000đ</td>
                <td>100.000.000đ</td>
                <td>1000</td>
            </tr>

        </tbody>
    </table>
</div>
