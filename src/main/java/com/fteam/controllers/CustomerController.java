/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.TaiKhoanNganHang;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private int countLoadPage = 0;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "customer_management", method = RequestMethod.GET)
    public String searchCustomers(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
        model.addAttribute("pageTitle", "Quản lý khách hàng");
        countLoadPage++;
        if(countLoadPage>2){
            httpSession.removeAttribute("messageCustomer");
            httpSession.removeAttribute("messageSuccessCustomer");
            countLoadPage=0;
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
                httpSession.setAttribute("messageCustomer", "Tên khách hàng không được bỏ trống!");
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
                httpSession.setAttribute("messageCustomer", "Độ tuổi phải lớn hơn hoặc bằng 15!");
                return "home/customer_management";
            }
            String GioiTinh = request.getParameter("GioiTinh");
            String DiaChi = request.getParameter("DiaChi");
            if (DiaChi == null || DiaChi.trim().isEmpty()) {
                httpSession.setAttribute("messageCustomer", "Địa chỉ không được để trống!");
                return "home/customer_management";
            }
            String CCCD = request.getParameter("CCCD");
            if (CCCD == null || CCCD.trim().isEmpty()) {
                httpSession.setAttribute("messageCustomer", "Căn cước công dân không được để trống!");
                return "home/customer_management";
            } else {
                // Kiểm tra CCCD có tồn tại trong cơ sở dữ liệu hay chưa
                KhachHang existingCustomer = (KhachHang) session.createQuery("FROM KhachHang WHERE CCCD = :CCCD")
                        .setParameter("CCCD", CCCD)
                        .uniqueResult();
                if (existingCustomer != null) {
                    // CCCD đã tồn tại trong cơ sở dữ liệu
                    httpSession.setAttribute("messageCustomer", "Căn cước công dân đã tồn tại!");
                    return "home/customer_management";
                }
            }
            String Email = request.getParameter("Email");
            if (Email == null || Email.trim().isEmpty()) {
                httpSession.setAttribute("messageCustomer", "Email không được để trống!");
                return "home/customer_management";
            }
            String SoDienThoai = request.getParameter("SoDienThoai");
            if (SoDienThoai == null || SoDienThoai.trim().isEmpty()) {
                httpSession.setAttribute("messageCustomer", "Số điện thoại không được để trống!");
                return "home/customer_management";
            }
            String NgheNghiep = request.getParameter("NgheNghiep");
            if (NgheNghiep == null || NgheNghiep.trim().isEmpty()) {
                httpSession.setAttribute("messageCustomer", "Số điện thoại không được để trống!");
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
            httpSession.setAttribute("messageSuccessCustomer", "Đăng ký thành công!");
            httpSession.removeAttribute("messageCustomer");
            return "home/customer_management";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            httpSession.setAttribute("messageCustomer", "Đăng ký thất bại!");
            return "home/customer_management";
        } finally {
            session.close();
        }
    }

    @Transactional
    @RequestMapping(value = "customer_management/{customerId}", method = RequestMethod.POST)
    public String deleteCustomer(HttpSession httpSession, @PathVariable int customerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Truy vấn khách hàng cần xóa từ database
            KhachHang khachHang = session.get(KhachHang.class, customerId);

            if (khachHang == null) {
                // Không tìm thấy khách hàng, thông báo lỗi
                httpSession.setAttribute("message", "Không tìm thấy khách hàng cần xóa!");
                return "home/customer_management";
            } else {
                // Xóa khách hàng khỏi database
                session.delete(khachHang);
                transaction.commit();
                httpSession.setAttribute("message", "Xóa khách hàng thành công!");
            }

            // Redirect về trang hiển thị danh sách khách hàng
            return "home/customer_management";
        } catch (Exception e) {
            // ...
        } finally {
            session.close();
        }
        return "home/customer_management";
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
            httpSession.setAttribute("message", "Xóa Thành Công!");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            httpSession.setAttribute("message", "Không thể xóa khách hàng!");
        } finally {
            session.close();
        }
        return "redirect:/home/customer_management";
    }

    @Transactional
    @RequestMapping(value = "customer_detail/{id}", method = RequestMethod.GET)
    public String CustomerDetail(Model model, @PathVariable("id") int ID_KhachHang) {
        try (Session session = sessionFactory.openSession()) {
            // Truy vấn khách hàng theo id
            String hql = "FROM KhachHang c WHERE c.ID_KhachHang = :customerId";
            Query query = session.createQuery(hql);
            query.setParameter("customerId", ID_KhachHang);
            KhachHang customer = (KhachHang) query.uniqueResult();

            if (customer == null) {
                // Khách hàng không tồn tại
                model.addAttribute("message", "Khách hàng không tồn tại");
                return "redirect:/customer_management";
            } else {
                // Truy vấn tài khoản ngân hàng của khách hàng
                hql = "FROM TaiKhoanNganHang b WHERE b.ID_KhachHang = :customerId";
                query = session.createQuery(hql);
                query.setParameter("customerId", ID_KhachHang);
                List<TaiKhoanNganHang> bankAccounts = query.list();

                // Thêm thông tin khách hàng và tài khoản ngân hàng vào model
                model.addAttribute("customer", customer);
                model.addAttribute("bankAccounts", bankAccounts);
                System.out.print(customer.getCccd());
                return "home/customer_detail";
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            model.addAttribute("message", "Có lỗi xảy ra khi truy vấn dữ liệu");
            return "redirect:/home/customer_management";
        }
    }

}
