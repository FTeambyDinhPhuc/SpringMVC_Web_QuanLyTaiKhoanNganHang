/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.NhanVien;
import com.fteam.models.TaiKhoanNganHang;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class CustomerController {

    private String messageCustomer = null;
    private String messageSuccessCustomer = null;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "customer_management", method = RequestMethod.GET)
    public String searchCustomers(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
        model.addAttribute("pageTitle", "Quản lý khách hàng");
        if (messageCustomer != null || messageSuccessCustomer != null) {
            model.addAttribute("messageCustomer", messageCustomer);
            model.addAttribute("messageSuccessCustomer", messageSuccessCustomer);
            messageCustomer = null;
            messageSuccessCustomer = null;
        }
        String keyword = request.getParameter("searchcustomer");
        if (keyword == null) {
            keyword = "";
        }
        try (Session session = sessionFactory.openSession()) {

            String hql = "SELECT kh FROM KhachHang kh WHERE kh.cccd LIKE :keyword";

            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            List<KhachHang> khachhangs = query.list();
            List<KhachHang> searchResult = new ArrayList<>();
            // Search for staff with matching name
            for (KhachHang khachHang : khachhangs) {
                if (khachHang.getCccd().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResult.add(khachHang);
                }
            }
            if (searchResult.isEmpty()) {
                model.addAttribute("messageCustomer", "Không tìm thấy khách hàng!");
                return "home/customer_management";
            } else {
                model.addAttribute("khachhangs", searchResult);
                return "home/customer_management";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
            model.addAttribute("messageCustomer", "Có lỗi khi tìm kiếm khách hàng!");
            return "home/customer_management";
        }
    }

    @Transactional
    @RequestMapping(value = "customer_management", method = RequestMethod.POST)
    public String Customers(
            HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            String TenKhachHang = request.getParameter("TenKhachHang");
            if (TenKhachHang == null || TenKhachHang.trim().isEmpty()) {
                messageCustomer = "Tên khách hàng không được bỏ trống!";
                return "home/customer_management";
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
                messageCustomer = "Độ tuổi phải lớn hơn hoặc bằng 15!";
                return "home/customer_management";
            }
            String GioiTinh = request.getParameter("GioiTinh");
            String DiaChi = request.getParameter("DiaChi");
            if (DiaChi == null || DiaChi.trim().isEmpty()) {
                messageCustomer = "Địa chỉ không được để trống!";
                return "home/customer_management";
            }
            String CCCD = request.getParameter("CCCD");
            if (CCCD == null || CCCD.trim().isEmpty()) {
                messageCustomer = "Căn cước công dân không được để trống!";
                return "home/customer_management";
            } else {
                // Kiểm tra CCCD có tồn tại trong cơ sở dữ liệu hay chưa
                KhachHang existingCustomer = (KhachHang) session.createQuery("FROM KhachHang WHERE CCCD = :CCCD")
                        .setParameter("CCCD", CCCD)
                        .uniqueResult();
                if (existingCustomer != null) {
                    // CCCD đã tồn tại trong cơ sở dữ liệu
                    messageCustomer = "Căn cước công dân đã tồn tại!";
                    return "home/customer_management";
                }
            }
            String Email = request.getParameter("Email");
            if (Email == null || Email.trim().isEmpty()) {
                messageCustomer = "Email không được để trống!";
                return "home/customer_management";
            }
            String SoDienThoai = request.getParameter("SoDienThoai");
            if (SoDienThoai == null || SoDienThoai.trim().isEmpty()) {
                messageCustomer = "Số điện thoại không được để trống!";
                return "home/customer_management";
            }
            String NgheNghiep = request.getParameter("NgheNghiep");
            if (NgheNghiep == null || NgheNghiep.trim().isEmpty()) {
                messageCustomer = "Số điện thoại không được để trống!";
                return "home/customer_management";
            }
            // Tạo đối tượng KhachHang mới
            KhachHang newCustomer = new KhachHang();
            newCustomer.setTenKhachHang(TenKhachHang);
            newCustomer.setNgaySinhKH(NamSinh);
            newCustomer.setGioiTinh(GioiTinh);
            newCustomer.setDiaChiKH(DiaChi);
            newCustomer.setCccd(CCCD);
            newCustomer.setEmailKH(Email);
            newCustomer.setSoDienThoai(SoDienThoai);
            newCustomer.setNgheNghiep(NgheNghiep);
            // Bắt đầu giao dịch và lưu đối tượng KhachHang mới vào cơ sở dữ liệu
            transaction = session.beginTransaction();
            session.save(newCustomer);
            transaction.commit();
            messageSuccessCustomer = "Đăng ký thành công!";
            return "home/customer_management";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            messageCustomer = "Đăng ký thất bại!";
            return "home/customer_management";
        } finally {
            session.close();
        }
    }

    @Transactional
    @RequestMapping(value = "customer_management/delete", method = RequestMethod.POST)
    public String deleteCustomer(@RequestParam("deletecustomerId") int customerId, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String updateQuery = "DELETE FROM KhachHang WHERE ID_KhachHang = :id";
            Query query = session.createQuery(updateQuery);
            query.setParameter("id", customerId);
            query.executeUpdate();
            tx.commit();
            messageSuccessCustomer = "Xóa Thành Công!";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageCustomer = "Không thể xóa khách hàng!";
        } finally {
            session.close();
        }
        return "redirect:/home/customer_management";
    }

    @Transactional
    @RequestMapping(value = "customer_detail", method = RequestMethod.GET)
    public String CustomerDetail(Model model, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        String ID_KhachHang = request.getParameter("id");
        session.beginTransaction();
        try {
            // Truy vấn khách hàng theo id
            String selectCustomer = "FROM KhachHang WHERE ID_KhachHang = :customerId";
            Query query = session.createQuery(selectCustomer);
            query.setParameter("customerId", ID_KhachHang);
            KhachHang customer = (KhachHang) query.uniqueResult();
            model.addAttribute("customer", customer);
            if (customer == null) {
                // Khách hàng không tồn tại
                model.addAttribute("message", "Khách hàng không tồn tại");
                return "redirect:/home/customer_management";
            } else {
                // Truy vấn tài khoản ngân hàng của khách hàng
                String selectTK = "FROM TaiKhoanNganHang WHERE ID_KhachHang = :idCustomer";
                Query queryy = session.createQuery(selectTK)
                        .setParameter("idCustomer", ID_KhachHang);
                List<TaiKhoanNganHang> bankAccounts = queryy.list();

                // Thêm thông tin khách hàng và tài khoản ngân hàng vào model
                model.addAttribute("bankAccounts", bankAccounts);
                System.out.print(customer.getCccd());
                return "home/customer_detail";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Có lỗi xảy ra khi truy vấn dữ liệu");
            return "redirect:/home/customer_management";
        }
    }

    @Transactional
    @RequestMapping(value = "customer_management/edit", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("customer") KhachHang customer,
            HttpServletRequest request, ModelMap model,
            @RequestParam("editCustomerId") int customerId,
            HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String editQuery = "UPDATE KhachHang SET TenKhachHang=:tenKhachHang, NgaySinhKH=:namSinh, GioiTinh=:gioiTinh,"
                    + " DiaChiKH=:diaChi, EmailKH=:email, SoDienThoai=:soDienThoai, NgheNghiep=:ngheNghiep WHERE ID_KhachHang = :id";
            Query updateNhanVienQuery = session.createQuery(editQuery)
                    .setParameter("tenKhachHang", customer.getTenKhachHang())
                    .setParameter("namSinh", customer.getNgaySinhKH())
                    .setParameter("gioiTinh", customer.getGioiTinh())
                    .setParameter("diaChi", customer.getDiaChiKH())
                    .setParameter("email", customer.getEmailKH())
                    .setParameter("soDienThoai", customer.getSoDienThoai())
                    .setParameter("ngheNghiep", customer.getNgheNghiep())
                    .setParameter("id", customerId);
            updateNhanVienQuery.executeUpdate();
            tx.commit();
            messageSuccessCustomer = "Cập nhật thông tin khách hàng thành công!";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageCustomer = "Không thể cập nhật thông tin khách hàng!";
        } finally {
            session.close();
        }
        return "redirect:/home/customer_management";
    }
    @Transactional
    @RequestMapping(value = "customer_detail/addBankAccount", method = RequestMethod.POST)
    public String addBankAccount(
            HttpServletRequest request, ModelMap model,
            @RequestParam("customerId") int customerId,
            HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //laasy nagfy hienej taij
            LocalDate now = LocalDate.now();
            //layas ngayf sau 5 nawm
            LocalDate fiveyear = now.plusYears(5);
            //laasy soos taif khoanr random
            Random rand = new Random();
            int num = rand.nextInt(1000);
            String str = String.format("9701234%03d", num);
            String insertQuery = "INSERT INTO TaiKhoanNganHang(SoTaiKhoanNganHang, SoDuTaiKhoan,"
                    + " TrangThaiTaiKhoan, NgayMoTaiKhoan, NgayDongTaiKhoan, ID_KhachHang) "
                    + "VALUES (:SoTaiKhoanNganHang, :SoDuTaiKhoan, :TrangThaiTaiKhoan, :NgayMoTaiKhoan,"
                    + " :NgayDongTaiKhoan, :ID_KhachHang)";
            Query updateNhanVienQuery = session.createQuery(insertQuery)
                    .setParameter("SoTaiKhoanNganHang", str)
                    .setParameter("SoDuTaiKhoan", 0)
                    .setParameter("TrangThaiTaiKhoan", 1)
                    .setParameter("NgayMoTaiKhoan", now)
                    .setParameter("NgayDongTaiKhoan", fiveyear)
                    .setParameter("ID_KhachHang", customerId);
            updateNhanVienQuery.executeUpdate();
            tx.commit();
            return "redirect:/home/customer_detail/?id="+customerId;
//            httpSession.setAttribute("messageSuccessCustomer", "Cập nhật thông tin khách hàng!");
//            httpSession.removeAttribute("messageCustomer");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
//            httpSession.setAttribute("messageCustomer", "Không thể cập nhật thông tin khách hàng!");
//            httpSession.removeAttribute("messageSuccessCustomer");
        } finally {
            session.close();
        }
        return "redirect:/home/customer_detail/?id="+customerId;
    }
}
