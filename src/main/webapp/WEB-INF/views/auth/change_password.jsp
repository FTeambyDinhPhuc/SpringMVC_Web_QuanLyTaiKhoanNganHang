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
            <form class="input-boxes" action="change_password" method="post">
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        placeholder="Nhập mật khẩu cũ" name="matKhauCu"
                        required
                        />
                </div>      
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        placeholder="Nhập mật khẩu mới" name="matKhauMoi"
                        required
                        />
                </div>    
                <div class="input-box">
                    <i class="fa-solid fa-lock icon"></i>
                    <input
                        type="password"
                        placeholder="Xác nhận mật khẩu mới" name="nhapLaiMatKhauMoi"
                        required
                        />
                </div>
                <p class="warning-text">Chả có gì cả</p>

                <Button class="mybuton-primary auth-button"  type="submit"">
                    Xác nhận
                </Button>
            </form>
        </div>
    </div>
</div>