/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.PhieuGiaoDich;
import com.fteam.models.TaiKhoanNganHang;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class TransactionMoneyController {

    private String messageTransactionMoney = null;
    private String messageSuccessTransactionMoney = null;
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "transaction_money")
    public String TransactionMoney(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Nạp tiền - Rút tiền");
        String keyword = request.getParameter("searchSTK");
        if (messageTransactionMoney != null || messageSuccessTransactionMoney != null) {
            model.addAttribute("messageTransactionMoney", messageTransactionMoney);
            model.addAttribute("messageSuccessTransactionMoney", messageSuccessTransactionMoney);
            messageTransactionMoney = null;
            messageSuccessTransactionMoney = null;
        }
        try (Session session = sessionFactory.openSession()) {

            String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", keyword);

            List<TaiKhoanNganHang> account = query.list();
            if (!account.isEmpty()) {
                TaiKhoanNganHang accountBank = account.get(0);
                model.addAttribute("acbank", accountBank);
                return "home/transaction_money";
            } else {
//              messageTransactionMoney = "Không tìm thấy tài khoản!";
                return "home/transaction_money";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
//            model.addAttribute("messageBankCard", "Có lỗi khi tìm kiếm khách hàng!");
            return "home/transaction_money";
        }
    }

    @Transactional
    @RequestMapping(value = "depositModal/add", method = RequestMethod.POST)
    public String naptien(ModelMap model,HttpSession httpSession, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String sotaikhoan = request.getParameter("soTaiKhoanNganHang");
        String cancuoc = request.getParameter("canCuoc");
        int tiennap = parseInt(request.getParameter("soTienMuonNap"));
        int phi = parseInt(request.getParameter("phiGiaoDich"));
        int sdtk = parseInt(request.getParameter("sotienhientai"));
        String idnhanvien = request.getParameter("idnhanvien");
        try {
            tx = session.beginTransaction();

            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = LocalDate.now().getYear();
            Date now = new Date();
            long timestamp = now.getTime();
            int tong = tiennap + sdtk;

            String checkpass = "FROM KhachHang INNER JOIN TaiKhoanNganHang ON KhachHang.idKhachHang = TaiKhoanNganHang.khachHang WHERE TaiKhoanNganHang.soTaiKhoanNganHang =:stk";
            Query checkp = session.createQuery(checkpass);
            checkp.setParameter("stk", sotaikhoan);
            List<KhachHang> khachhangs = checkp.list();
            KhachHang khachHang = khachhangs.get(0);
            String cCccd = khachHang.getCccd();
            if (cCccd != cancuoc) {
                messageTransactionMoney = "Căn cước công dân không trùng khớp!";
                return "home/transaction_money";
            }
            String editQuery = "UPDATE TaiKhoanNganHang SET SoDuTaiKhoan=:sodu,"
                    + "  WHERE ID_KhachHang = :id";
            Query updateNhanVienQuery = session.createQuery(editQuery)
                    .setParameter("sodu", tong)
                    .setParameter("id", sotaikhoan);
            updateNhanVienQuery.executeUpdate();

            String a = "Nạp tiền vào tài khoản";
            String insertQuery = "INSERT INTO PhieuGiaoDich(ID_GiaoDich, SoTaiKhoanNganHang, "
                    + "ID_NhanVien, SoTienGiaoDich, "
                    + "NgayGiaoDich, ThangGiaoDich, NamGiaoDich, ThoiGianGiaoDich, "
                    + "NoiDungGiaoDich, TrangThaiGiaoDich,TaiKhoanNguoiNhan_Gui,PhiGiaoDich) "
                    + "VALUES (:ID_GiaoDich, :SoTaiKhoanNganHang, :ID_NhanVien, :SoTienGiaoDich, :NgayGiaoDich, "
                    + ":ThangGiaoDich, :NamGiaoDich, :ThoiGianGiaoDich, :NoiDungGiaoDich,"
                    + " :TrangThaiGiaoDich, :TaiKhoanNguoiNhan_Gui, :PhiGiaoDich)";
            Query query = session.createQuery(insertQuery)
                    .setParameter("ID_GiaoDich", 2)
                    .setParameter("SoTaiKhoanNganHang", sotaikhoan)
                    .setParameter("ID_NhanVien", idnhanvien)
                    .setParameter("SoTienGiaoDich", tiennap)
                    .setParameter("NgayGiaoDich", day)
                    .setParameter("ThangGiaoDich", month)
                    .setParameter("NamGiaoDich", year)
                    .setParameter("ThoiGianGiaoDich", timestamp)
                    .setParameter("NoiDungGiaoDich", a)
                    .setParameter("TrangThaiGiaoDich", 1)
                    .setParameter("TaiKhoanNguoiNhan_Gui", sotaikhoan)
                    .setParameter("PhiGiaoDich", phi);
            query.executeUpdate();
            tx.commit();
            messageSuccessTransactionMoney = "Nạp tiền thành công!";
            return "redirect:/home/transaction_money";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageTransactionMoney = "Nạp tiền không thành công!";
            return "redirect:/home/transaction_money";
        } finally {
            session.close();
        }
    }
}
