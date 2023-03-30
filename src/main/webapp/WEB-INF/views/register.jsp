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
    <body >
        <div class="auth-background">
            <div class="auth-container shadow register-auth-container">
                <div class="auth-form-content register-auth-form-content">
                    <div class="title">Đăng ký nhân viên</div>
                    <form class="input-boxes" modelAttribute="nhanvien" action="register.htm"method="post">

                        <div class="input-box">
                            <i class="fa-solid fa-user icon"></i>
                            <input
                                type="text"
                                placeholder="Nhập tên đăng nhập"
                                path="TenDangNhap" id="TenDangNhap" name="TenDangNhap"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb1" >${messuser} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-user icon"></i>
                            <input
                                type="text"
                                placeholder="Nhập tên nhân viên"
                                path="TenNhanVien" id="TenNhanVien" name="TenNhanVien"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb2" >${messname} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-calendar-days icon"></i>
                            <input
                                type="date"
                                path="NamSinh" id="NamSinh" name="NamSinh"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb3" >${message} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-person icon"></i>
                            <select path="GioiTinh" id="GioiTinh" class="input" name="GioiTinh" required>
                                <option value="1" type="input">Nam</option>
                                <option value="0"  type="input">Nữ</option>
                            </select>
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-location-dot icon"></i>
                            <input
                                type="text"
                                path="DiaChi" id="DiaChi" name="DiaChi"
                                placeholder="Nhập địa chỉ"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb4" >${mesdir} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-envelope icon"></i>
                            <input
                                type="Email"
                                path="Email" id="Email" name="Email"
                                placeholder="Nhập email"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb5" >${messemail} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-address-card icon"></i>
                            <input
                                type="text"
                                path="CCCD" id="CCCD" name="CCCD"
                                placeholder="CCCD: 123456789123"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb6" >${messcccd} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-phone icon"></i>
                            <input
                                type="tel"
                                path="SoDienThoai" id="SoDienThoai" name="SoDienThoai"
                                placeholder="Nhập số điện thoại"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb7" >${messphone} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="password"
                                placeholder="Nhập mật khẩu" name="MatKhau"
                                path="MatKhau" id="MatKhau"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb8" >${messpass} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="password"
                                placeholder="Xác nhận mật khẩu" name="repassword"
                                path="repassoword" id="repassoword"
                                required
                                />
                        </div>
                        <h3 style="color: red" id="tb9" >${messrepass} </h3>
                        <div class="input-box">
                            <i class="fa-solid fa-clipboard-user icon"></i>
                            <select name="chucVu" id="chucVu" class="input">
                                <c:forEach items="${chucVuList}" var="chucVu">
                                    <option value="${chucVu.id}">${chucVu.tenChucVu}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <Button class="mybuton-primary auth-button"  type="submit" onclick="clearMessage()">
                            Xác nhận
                        </Button>


                    </form>
                </div>
            </div>
        </div>

        <script src="<c:url value="/assets/js/jquery-3.5.1.min.js" />"></script>
        <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/assets/js/main.js" />"></script>
        <script >
            function clearMessage() {
                $("tb1").innerText = '';
                $("tb2").innerText = '';
                $("tb3").innerText = '';
                $("tb4").innerText = '';
                $("tb5").innerText = '';
                $("tb6").innerText = '';
                $("tb7").innerText = '';
                $("tb8").innerText = '';
                $("tb9").innerText = '';
                
            }
        </script>    
        <script>
            // Lưu giá trị chức vụ trong localStorage khi người dùng thay đổi giá trị
            document.getElementById("chucVu").addEventListener("change", function () {
                localStorage.setItem("selectedChucVu", this.value);
            });

            // Khôi phục giá trị chức vụ từ localStorage mỗi khi trang web được tải lại
            window.onload = function () {
                var selectedChucVu = localStorage.getItem("selectedChucVu");
                if (selectedChucVu) {
                    document.getElementById("chucVu").value = selectedChucVu;
                }
            };

            $(document).ready(function () {
                setTimeout(function () {
                    $("#tb1").css("display", "none");
                    $("#tb2").css("display", "none");
                    $("#tb3").css("display", "none");
                    $("#tb4").css("display", "none");
                    $("#tb5").css("display", "none");
                    $("#tb6").css("display", "none");
                    $("#tb7").css("display", "none");
                    $("#tb8").css("display", "none");
                    $("#tb9").css("display", "none");
                    
                }, 10000); // 10 seconds
            });
        </script>


    </body>

</html>
a