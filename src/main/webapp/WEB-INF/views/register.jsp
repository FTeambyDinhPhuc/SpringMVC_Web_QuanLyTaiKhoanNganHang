<%-- 
    Document   : register
    Created on : Mar 14, 2023, 10:42:29 AM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/fontawesome-free-6.3.0-web/css/all.min.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/css/main.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/css/base.css" />"/>

        <title>Đăng ký nhân viên</title>
    </head>
    <body>
        <div class="auth-background">
            <div class="auth-container shadow register-auth-container">
                <div class="auth-form-content register-auth-form-content">
                    <div class="title">Đăng ký nhân viên</div>
                    <form class="input-boxes" modelAttribute="NhanVien" action="register.htm"method="POST">
                        <div class="input-box">
                            <i class="fa-solid fa-user icon"></i>
                            <input
                                type="text"
                                placeholder="Nhập tên đăng nhập"
                                path="tenDangNhap" id="tenDangNhap"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-user icon"></i>
                            <input
                                type="text"
                                placeholder="Nhập tên nhân viên"
                                path="tenNhanVien" id="tenNhanVien"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-calendar-days icon"></i>
                            <input
                                type="date"
                                path="namSinh" id="namSinh"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-person icon"></i>
                            <select path="gioiTinh" id="gioiTinh" class="input" required>
                                <option value="1" type="input">Nam</option>
                                <option value="0"  type="input">Nữ</option>
                            </select>

                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-location-dot icon"></i>
                            <input
                                type="text"
                                path="diaChi" id="diaChi"
                                placeholder="Nhập địa chỉ"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-envelope icon"></i>
                            <input
                                type="email"
                                path="email" id="email"
                                placeholder="Nhập email"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-address-card icon"></i>
                            <input
                                type="text"
                                path="cccd" id="cccd"
                                placeholder="Nhập căn cước công dân"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-phone icon"></i>
                            <input
                                type="tel"
                                path="soDienThoai" id="soDienThoai"
                                placeholder="Nhập số điện thoại"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="password"
                                placeholder="Nhập mật khẩu"
                                path="matKhau" id="matKhau"
                                required
                                />
                        </div>
<!--                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="text"
                                placeholder="Trạng thái"
                                path="trangThaiTaiKhoan" id="trangThaiTaiKhoan"
                                required
                                />
                        </div>-->
<!--                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="password"
                                placeholder="Xác nhận mật khẩu"
                                path="tenDangNhap" id="tenDangNhap"
                                required
                                />
                        </div>-->
                        <div class="input-box">
                            <i class="fa-solid fa-clipboard-user icon"></i>
                            <select path="chucVu" id="chucVu" class="input" required>
                                <!--                                kéo từ database lên -->
                                <option  path="chucVu" id="chucVu" value="2">Nhân viên</option> 
                                <option  path="chucVu" id="chucVu" value="1">Quản lý</option>
                            </select>
                        </div>
                        <Button class="mybuton-primary auth-button"  type="submit">
                            Xác nhận
                        </Button>

                    </form>

                </div>
            </div>
        </div>

        <script src="<c:url value="/assets/js/jquery-3.5.1.min.js" />"></script>
        <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/assets/js/main.js" />"></script>
    </body>
</html>
