<%-- 
    Document   : register
    Created on : Mar 14, 2023, 10:42:29 AM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="auth-background">
    <div class="auth-container shadow register-auth-container">
        <div class="auth-form-content register-auth-form-content">
            <div class="title">Đăng ký nhân viên</div>
            <form class="input-boxes" modelAttribute="nhanvien" action="register" method="post">
                <div class="input-box">
                    <i class="fa-solid fa-user icon"></i>
                    <input
                        type="text"
                        placeholder="Nhập tên đăng nhập"
                        name="TenDangNhap"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-user icon"></i>
                    <input
                        type="text"
                        placeholder="Nhập tên nhân viên"
                        name="TenNhanVien"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-calendar-days icon"></i>
                    <input
                        type="date"
                        name="NamSinh"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-person icon"></i>
                    <select class="input" name="GioiTinh" required>
                        <option value="1" type="input">Nam</option>
                        <option value="0"  type="input">Nữ</option>
                    </select>
                </div>
                <div class="input-box">
                    <i class="fa-solid fa-location-dot icon"></i>
                    <input
                        type="text"
                        name="DiaChi"
                        placeholder="Nhập địa chỉ"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-envelope icon"></i>
                    <input
                        type="Email"
                        name="Email"
                        placeholder="Nhập email"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-address-card icon"></i>
                    <input
                        type="text"
                        name="CCCD"
                        placeholder="CCCD: 123456789123"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-phone icon"></i>
                    <input
                        type="tel"
                        name="SoDienThoai"
                        placeholder="Nhập số điện thoại"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        placeholder="Nhập mật khẩu"
                        name="MatKhau"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        placeholder="Xác nhận mật khẩu"
                        name="repassword"
                        required
                        />
                </div>

                <div class="input-box">
                    <i class="fa-solid fa-clipboard-user icon"></i>
                    <select name="chucVu" class="input">
                        <c:forEach items="${chucVuList}" var="chucVu">
                            <option value="${chucVu.id}">${chucVu.tenChucVu}</option>
                        </c:forEach>
                    </select>
                </div>
                <p class="warning-text">${message}</p>
                <Button class="mybuton-primary auth-button" type="submit">
                    Xác nhận
                </Button>
            </form>
        </div>
    </div>
</div>
