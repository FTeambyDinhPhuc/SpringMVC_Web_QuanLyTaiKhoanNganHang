<%-- 
    Document   : login
    Created on : Mar 14, 2023, 9:05:39 AM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="auth-background">
    <div class="auth-container shadow">
        <div class ="auth-cover">
            <img src="<c:url value="/assets/images/img_bg_auth.jpg" />" alt="Image background authentication" />
        </div>
        <div class="auth-form-content">
            <div class="title">${pageTitle}</div>
            <form class="input-boxes" modelAttribute="NhanVien" action="login" method="POST" >
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
                <c:if test="${not empty message}">
                    <p class="text-danger">${message}</p>
                </c:if>

                <Button class="mybuton-primary auth-button"  type="submit">
                    Đăng nhập
                </Button>
            </form>

        </div>
    </div>
</div>

