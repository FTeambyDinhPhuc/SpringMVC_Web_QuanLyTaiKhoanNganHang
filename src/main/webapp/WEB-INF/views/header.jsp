<%-- 
    Document   : header
    Created on : Mar 13, 2023, 4:41:31 PM
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


        <title>Quản lý tài khoản ngân hàng</title>
    </head>
    <body class="nav-fixed">
        <nav class="topnav navbar navbar-dark navbar-expand bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand" href="@Url.Action("Statistical","Statistical")">FTeam Bank</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-lg order-1 order-lg-0 mr-xl-auto" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <ul class="navbar-nav ml-auto mr-3 mr-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user fa-fw"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="">Đổi mật khẩu</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sidenav">
                    <div class="sidenav-menu">
                        <div class="nav">
                            <div class="sidenav-menu-heading">Thống kê</div>
                            <a class="nav-link" href="">
                                <div class="nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Thống kê
                            </a>
                            <div class="sidenav-menu-heading">Quản lý</div>
                            <!--                            <a class="nav-link collapsed" data-toggle="collapse" href="#collapseLayouts" role="button" aria-expanded="false" aria-controls="collapseLayouts">
                                                            <div class="nav-link-icon"><i class="fa-solid fa-box"></i></div>
                                                            Loại sản phẩm
                                                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                                        </a>
                                                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                                            <nav class="sidenav-menu-nested nav">
                                                                <a class="nav-link" href="@Url.Action("Size","Manage")">Size</a>
                                                                <a class="nav-link" href="@Url.Action("TypesClothes","Manage")">Loại đồ</a>
                                                            </nav>
                                                        </div>
                                                        <a class="nav-link" href="@Url.Action("Product","Manage")">
                                                            <div class="nav-link-icon"><i class="fa-solid fa-shirt"></i></div>
                                                            Sản phẩm
                                                        </a>-->
                            <a class="nav-link" href="./staff_management">
                                <div class="nav-link-icon"><i class="fa-solid fa-clipboard-user"></i></div>
                                Nhân viên
                            </a>
                            <a class="nav-link" href="">
                                <div class="nav-link-icon"><i class="fa-solid fa-users"></i></div>
                                Tài khoản khách hàng
                            </a>
                            <a class="nav-link" href="">
                                <div class="nav-link-icon"><i class="fa-solid fa-file-invoice"></i></div>
                                Hóa đơn
                            </a>

                            <div class="sidenav-menu-heading">Chức năng</div>

                            <a class="nav-link" href="">
                                <div class="nav-link-icon"><i class="fas fa-table"></i></div>
                                Feedback
                            </a>
                        </div>
                    </div>
                    <div class="sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Anh Tèo
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>