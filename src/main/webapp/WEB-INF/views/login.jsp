<%-- 
    Document   : login
    Created on : Mar 14, 2023, 9:05:39 AM
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

        <title>Đăng nhập QLTKNH</title>
    </head>
    <body>
        <div class="auth-background">
            <div class="auth-container shadow">
                <div class ="auth-cover">
                    <img src="<c:url value="/assets/images/img_bg_auth.jpg" />" alt="Image background authentication" />
                </div>
                <div class="auth-form-content">
                    <div class="title">Đăng nhập</div>
                    <form class="input-boxes"modelAttribute="NhanVien" action="login.htm" method="POST" >
                        <div class="input-box">
                            <i class="fa-solid fa-envelope icon"></i>
                            <input
                                type="text"
                                placeholder="Nhập tên đăng nhập"
                                id="TenDangNhap" name="TenDangNhap"
                                required
                                />
                        </div>
                        <div class="input-box">
                            <i class="fa-solid fa-lock icon"></i>
                            <input
                                type="password"
                                placeholder="Nhập mật khẩu"
                                id="MatKhau" name="MatKhau"
                                required
                                />
                        </div>

                        <Button class="mybuton-primary auth-button"  type="submit">
                            Đăng nhập
                        </Button>
                    </form>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger">${message}</div>
                    </c:if>
                </div>
            </div>
        </div>

        <script src="<c:url value="/assets/js/jquery-3.5.1.min.js" />"></script>
        <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/assets/js/main.js" />"></script>

    </body>
</html>
