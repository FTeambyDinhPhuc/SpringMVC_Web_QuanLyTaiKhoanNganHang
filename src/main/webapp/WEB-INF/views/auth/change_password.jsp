<%-- 
    Document   : change_password
    Created on : Apr 1, 2023, 3:11:03 PM
    Author     : dinhp
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="auth-background">
    <div class="auth-container shadow register-auth-container">
        <div class="auth-form-content register-auth-form-content">
            <div class="title">${pageTitle}</div>
            <form class="input-boxes" modelAttribute="changepass" action="change_password" method="post">
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        path="matKhauCu" id="matKhauCu"
                        placeholder="Nhập mật khẩu cũ" name="matKhauCu"
                        required
                        />
                </div>      
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        path="matKhau" id="matKhau"
                        placeholder="Nhập mật khẩu mới" name="matKhau"
                        required
                        />
                </div>    
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        path="matKhauXN" id="matKhauXN"
                        placeholder="Xác nhận mật khẩu mới" name="matKhauXN"
                        required
                        />
                </div>
                <p class="warning-text">${messageErrorChangePass}</p>

                <Button class="mybuton-primary auth-button"  type="submit"">
                    Xác nhận
                </Button>
            </form>
        </div>
    </div>
</div>