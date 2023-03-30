/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.ChucVu;
import com.fteam.models.NhanVien;
import java.time.LocalDate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dinhp
 */
@Controller
public class AuthController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(ModelMap model) {
        model.addAttribute("NhanVien", new NhanVien());
        return "login";
    }

    @Transactional
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, ModelMap model, @ModelAttribute("NhanVien") NhanVien NhanVien) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM NhanVien u WHERE u.tenDangNhap = :tenDangNhap";
            Query query = session.createQuery(hql);

            query.setParameter("tenDangNhap", NhanVien.getTenDangNhap());

            List<NhanVien> list = query.list();
            if (!list.isEmpty()) {
                // User exists, check password
                NhanVien loggedInNhanVien = list.get(0);
                String hashedPassword = loggedInNhanVien.getMatKhau();
                if (BCrypt.checkpw(NhanVien.getMatKhau(), hashedPassword)) {
                    // Login successful
                    model.addAttribute("loggedInNhanVien", loggedInNhanVien);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("Ten", loggedInNhanVien.getTenNhanVien());
                    httpSession.setAttribute("chucvu", loggedInNhanVien.getChucVu().getId());

                    return "index";
                }
            }
            // Login failed
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng !");
            return "login";
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            model.addAttribute("message", "Có lỗi xảy ra khi đăng nhập!");
            return "login";
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @RequestMapping(value = "/register")
    public String register(Model model) {
        Session session = sessionFactory.openSession();
        String hql = "FROM ChucVu";
        Query query = session.createQuery(hql);
        List<ChucVu> chucVuList = query.list();
        model.addAttribute("chucVuList", chucVuList);
        return "register";
    }

    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        String TenDangNhap = request.getParameter("TenDangNhap");
        if (TenDangNhap == null || TenDangNhap.isEmpty()) {
            httpSession.setAttribute("messuser", "Tên đăng nhập không được bỏ trống!");
            return "redirect:/register";
        }
        String TenNhanVien = request.getParameter("TenNhanVien");
        if (TenNhanVien == null || TenNhanVien.trim().isEmpty()) {
            httpSession.setAttribute("messpass", "Tên nhân viên không được bỏ trống!");
            return "redirect:/register";
        }
        String NamSinh = request.getParameter("NamSinh");
        // Chuyển đổi NamSinh sang LocalDate
        LocalDate birthdate = LocalDate.parse(NamSinh);

        // Lấy ngày hiện tại
        LocalDate now = LocalDate.now();

        // Tính toán độ tuổi bằng cách trừ năm sinh từ năm hiện tại
        int age = now.getYear() - birthdate.getYear();

        // Kiểm tra độ tuổi
        if (age < 15) {
            // Xử lý khi NamSinh không hợp lệ
            httpSession.setAttribute("message", "Độ tuổi phải lớn hơn hoặc bằng 15!");
            return "redirect:/register";
        }
        String GioiTinh = request.getParameter("GioiTinh");
        String DiaChi = request.getParameter("DiaChi");
        if (DiaChi == null || DiaChi.trim().isEmpty()) {
            httpSession.setAttribute("messdir", "Địa chỉ không được để trống!");
            return "redirect:/register";
        }
        String Email = request.getParameter("Email");
        if (Email == null || Email.trim().isEmpty()) {
            httpSession.setAttribute("messemail", "Email không được để trống!");
            return "redirect:/register";
        }
        String CCCD = request.getParameter("CCCD");
        if (CCCD == null || CCCD.trim().isEmpty()) {
            httpSession.setAttribute("messcccd", "Căn cước công dân không được để trống!");
            return "redirect:/register";
        }
        String SoDienThoai = request.getParameter("SoDienThoai");
        if (SoDienThoai == null || SoDienThoai.trim().isEmpty()) {
            httpSession.setAttribute("messphone", "Số điện thoại không được để trống!");
            return "redirect:/register";
        }
        String MatKhau = request.getParameter("MatKhau");
        if (MatKhau == null || MatKhau.trim().isEmpty()) {
            httpSession.setAttribute("messpass", "Mật khẩu không được để trống!");
            return "redirect:/register";
        }
        String repassword = request.getParameter("repassword");
        if (!MatKhau.equals(repassword)) {
            httpSession.setAttribute("messrepass", "Mật khẩu không khớp!");
            return "redirect:/register";
        }
        try {
            // Check if username is already existed
            Query query = session.createQuery("FROM NhanVien WHERE TenDangNhap = :TenDangNhap");
            query.setParameter("TenDangNhap", TenDangNhap);
            NhanVien nhanvien = (NhanVien) query.uniqueResult();
            if (nhanvien != null) {
                httpSession.setAttribute("message", "Tên đăng nhập đã tồn tại");
                return "redirect:/register";
            }
            // Get ID of ChucVu
            int chucVuId = Integer.parseInt(request.getParameter("chucVu"));
            ChucVu chucvu = session.get(ChucVu.class, chucVuId);
            String salt = BCrypt.gensalt(10);

            // Hash password with salt
            String hashedPassword = BCrypt.hashpw(MatKhau, salt);

            // Create new user
            nhanvien = new NhanVien(TenDangNhap, TenNhanVien, NamSinh, GioiTinh, DiaChi, CCCD, Email, SoDienThoai, hashedPassword, chucvu);
            String insertQuery = "INSERT INTO NhanVien(TenDangNhap, TenNhanVien, NamSinh, GioiTinh, DiaChi, CCCD, Email, SoDienThoai, MatKhau, ID_ChucVu) "
                    + "VALUES (:TenDangNhap, :TenNhanVien, :NamSinh, :GioiTinh, :DiaChi, :CCCD,:Email,  :SoDienThoai, :hashedPassword, :ID_ChucVu)";
            Query insertNhanVienQuery = session.createSQLQuery(insertQuery)
                    .setParameter("TenDangNhap", nhanvien.getTenDangNhap())
                    .setParameter("TenNhanVien", nhanvien.getTenNhanVien())
                    .setParameter("NamSinh", nhanvien.getNamSinh())
                    .setParameter("GioiTinh", nhanvien.getGioiTinh())
                    .setParameter("Email", nhanvien.getEmail())
                    .setParameter("DiaChi", nhanvien.getDiaChi())
                    .setParameter("CCCD", nhanvien.getCccd())
                    .setParameter("SoDienThoai", nhanvien.getSoDienThoai())
                    .setParameter("hashedPassword", nhanvien.getMatKhau())
                    .setParameter("ID_ChucVu", chucvu.getId());
            transaction = session.beginTransaction();
            insertNhanVienQuery.executeUpdate();
            transaction.commit();
            httpSession.setAttribute("message", "Đăng ký thành công");
            return "redirect:/staff_management";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            httpSession.setAttribute("message", "Đăng ký thất bại");
            return "redirect:/register";
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // code xử lý đăng xuất
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // xóa session
        }
        return "redirect:/login"; // chuyển hướng tới trang đăng nhập
    }

}
