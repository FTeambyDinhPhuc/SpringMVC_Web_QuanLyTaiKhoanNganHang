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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        if (keyword != null && !keyword.isEmpty()) {
            try ( Session session = sessionFactory.openSession()) {

                String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
                Query query = session.createQuery(hql);
                query.setParameter("keyword", keyword);
                TaiKhoanNganHang taiKhoanNganHang11 = (TaiKhoanNganHang) query.uniqueResult();
                if (taiKhoanNganHang11 == null) {
                    // Tài khoản không tồn tại
                    messageTransactionMoney = "Tài khoản không tồn tại!";
                    return "redirect:/home/transaction_money";
                }
                List<TaiKhoanNganHang> account = query.list();

                boolean isAccountEmpty = account.isEmpty();
                if (!isAccountEmpty) {
                    TaiKhoanNganHang accountBank = account.get(0);
                    httpSession.setAttribute("soTaiKhoan9", accountBank.getSoTaiKhoanNganHang());
                    httpSession.setAttribute("soDuTaiKhoan9", accountBank.getSoDuTaiKhoan());
                    httpSession.setAttribute("trangThaiTaiKhoan9", accountBank.getTrangThaiTaiKhoan());
                    httpSession.setAttribute("ngayMoTaiKhoan9", accountBank.getNgayMoTaiKhoan());

                    String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";
                    Query checkp = session.createQuery(checkpass);
                    checkp.setParameter("stk", httpSession.getAttribute("soTaiKhoan9"));
                    List<KhachHang> khachhangs = checkp.list();
                    KhachHang khachHang = khachhangs.get(0);
                    httpSession.setAttribute("tenKhachHang9", khachHang.getTenKhachHang());
                    return "home/transaction_money";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "home/transaction_money";
            }
        }
        return "home/transaction_money";
    }

    @Transactional
    @RequestMapping(value = "depositModal/add", method = RequestMethod.POST)
    public String naptien(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        if (httpSession.getAttribute("soTaiKhoan9") == null) {
            messageTransactionMoney = "Thông tin chuyển khoản trống";
            return "redirect:/home/transfer_money";
        }
        String soTaiKhoanNganHang = request.getParameter("soTaiKhoanNganHang");
        String cancuoc = request.getParameter("canCuoc");
        int tiennap = parseInt(request.getParameter("soTienMuonNap"));
        float phi = Float.parseFloat(request.getParameter("phiGiaoDich"));
        long sdtk = (long) httpSession.getAttribute("soDuTaiKhoan9");
        int idnhanvien = (int) httpSession.getAttribute("idNhanVien");
        try {
            tx = session.beginTransaction();
            Object trangThaiTaiKhoan = httpSession.getAttribute("trangThaiTaiKhoan9");
            if (trangThaiTaiKhoan == null || Integer.parseInt(trangThaiTaiKhoan.toString()) == 0) {
                messageTransactionMoney = "Tài khoản bị khóa không thể Nạp/Rút";
                return "redirect:/home/transaction_money";
            }
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = LocalDate.now().getYear();
            Date now = new Date();
            long timestamp = now.getTime();
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            long tong = tiennap + sdtk;
            long sotaikhoanLong = Long.parseLong(soTaiKhoanNganHang);
            String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";

            Query checkp = session.createQuery(checkpass);
            checkp.setParameter("stk", sotaikhoanLong);
            List<KhachHang> khachhangs = checkp.list();
            KhachHang khachHang = khachhangs.get(0);
            String cCccd = khachHang.getCccd();
            if (cCccd == null ? cancuoc != null : !cCccd.equals(cancuoc)) {
                messageTransactionMoney = "Căn cước công dân không trùng khớp!";
                return "redirect:/home/transaction_money";
            }

            String editQuery = "UPDATE TaiKhoanNganHang t SET t.soDuTaiKhoan = :sodu WHERE t.khachHang.id = :id and t.soTaiKhoanNganHang=:stk";
            Query updateNhanVienQuery = session.createQuery(editQuery)
                    .setParameter("sodu", tong)
                    .setParameter("stk", sotaikhoanLong)
                    .setParameter("id", khachHang.getIdKhachHang());
            updateNhanVienQuery.executeUpdate();

            String a = "Nạp tiền vào tài khoản";
            String insertQuery = "INSERT INTO PhieuGiaoDich(ID_GiaoDich, SoTaiKhoanNganHang, "
                    + "ID_NhanVien, SoTienGiaoDich, "
                    + "NgayGiaoDich, ThangGiaoDich, NamGiaoDich, ThoiGianGiaoDich, "
                    + "NoiDungGiaoDich, TrangThaiGiaoDich,TaiKhoanNguoiNhan_Gui,PhiGiaoDich) "
                    + "VALUES (:ID_GiaoDich, :SoTaiKhoanNganHang, :ID_NhanVien, :SoTienGiaoDich, :NgayGiaoDich, "
                    + ":ThangGiaoDich, :NamGiaoDich, :ThoiGianGiaoDich, :NoiDungGiaoDich,"
                    + " :TrangThaiGiaoDich, :TaiKhoanNguoiNhan_Gui, :PhiGiaoDich)";
            Query query = session.createSQLQuery(insertQuery)
                    .setParameter("ID_GiaoDich", 2)
                    .setParameter("SoTaiKhoanNganHang", sotaikhoanLong)
                    .setParameter("ID_NhanVien", idnhanvien)
                    .setParameter("SoTienGiaoDich", tiennap)
                    .setParameter("NgayGiaoDich", day)
                    .setParameter("ThangGiaoDich", month)
                    .setParameter("NamGiaoDich", year)
                    .setParameter("ThoiGianGiaoDich", localDateTime)
                    .setParameter("NoiDungGiaoDich", a)
                    .setParameter("TrangThaiGiaoDich", 1)
                    .setParameter("TaiKhoanNguoiNhan_Gui", sotaikhoanLong)
                    .setParameter("PhiGiaoDich", phi);
            query.executeUpdate();
            tx.commit();
            //clear session
            httpSession.removeAttribute("soTaiKhoan9");
            httpSession.removeAttribute("soDuTaiKhoan9");
            httpSession.removeAttribute("trangThaiTaiKhoan9");
            httpSession.removeAttribute("ngayMoTaiKhoan9");
            httpSession.removeAttribute("tenKhachHang9");
            messageSuccessTransactionMoney = "Nạp tiền thành công!";
            return "redirect:/home/transaction_money";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            messageTransactionMoney = "Nạp tiền không thành công!";
            return "home/transaction_money";
        } finally {
            session.close();
        }
    }

    @Transactional
    @RequestMapping(value = "depositModal/rut", method = RequestMethod.POST)
    public String ruttien(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        if (httpSession.getAttribute("soTaiKhoan9") == null) {
            messageTransactionMoney = "Thông tin chuyển khoản trống";
            return "redirect:/home/transfer_money";
        }
        String soTaiKhoanNganHangrut = request.getParameter("soTaiKhoanNganHangrut");
        String cancuoc = request.getParameter("canCuoc1");
        int tienrut = parseInt(request.getParameter("soTienMuonRut1"));
        float phi = Float.parseFloat(request.getParameter("phiGiaoDich1"));
        long sdtk = (long) httpSession.getAttribute("soDuTaiKhoan9");
        int idnhanvien = (int) httpSession.getAttribute("idNhanVien");
        try {
            tx = session.beginTransaction();
            if (tienrut > sdtk) {
                messageTransactionMoney = "So du khong du!";
                return "redirect:/home/transaction_money";
            }
            Object trangThaiTaiKhoan = httpSession.getAttribute("trangThaiTaiKhoan9");
            if (trangThaiTaiKhoan == null || Integer.parseInt(trangThaiTaiKhoan.toString()) == 0) {
                messageTransactionMoney = "Tài khoản bị khóa không thể Nạp/Rút";
                return "redirect:/home/transaction_money";
            }
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = LocalDate.now().getYear();
            Date now = new Date();
            long timestamp = now.getTime();
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            long hieu = sdtk - tienrut;
            long sotaikhoanLong = Long.parseLong(soTaiKhoanNganHangrut);
            String check = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";

            Query checkcccd = session.createQuery(check);
            checkcccd.setParameter("stk", sotaikhoanLong);
            List<KhachHang> khachhangs = checkcccd.list();
            KhachHang khachHang = khachhangs.get(0);
            String cCccd = khachHang.getCccd();
            if (cCccd == null ? cancuoc != null : !cCccd.equals(cancuoc)) {
                messageTransactionMoney = "Căn cước công dân không trùng khớp!";
                return "redirect:/home/transaction_money";
            }
            String editQuery = "UPDATE TaiKhoanNganHang t SET t.soDuTaiKhoan = :sodu WHERE t.khachHang.id = :id and t.soTaiKhoanNganHang=:stk";
            Query updateNhanVienQuery = session.createQuery(editQuery)
                    .setParameter("sodu", hieu)
                    .setParameter("stk", sotaikhoanLong)
                    .setParameter("id", khachHang.getIdKhachHang());
            updateNhanVienQuery.executeUpdate();

            String a = "Nạp tiền vào tài khoản";
            String insertQuery = "INSERT INTO PhieuGiaoDich(ID_GiaoDich, SoTaiKhoanNganHang, "
                    + "ID_NhanVien, SoTienGiaoDich, "
                    + "NgayGiaoDich, ThangGiaoDich, NamGiaoDich, ThoiGianGiaoDich, "
                    + "NoiDungGiaoDich, TrangThaiGiaoDich,TaiKhoanNguoiNhan_Gui,PhiGiaoDich) "
                    + "VALUES (:ID_GiaoDich, :SoTaiKhoanNganHang, :ID_NhanVien, :SoTienGiaoDich, :NgayGiaoDich, "
                    + ":ThangGiaoDich, :NamGiaoDich, :ThoiGianGiaoDich, :NoiDungGiaoDich,"
                    + " :TrangThaiGiaoDich, :TaiKhoanNguoiNhan_Gui, :PhiGiaoDich)";
            Query query = session.createSQLQuery(insertQuery)
                    .setParameter("ID_GiaoDich", 1)
                    .setParameter("SoTaiKhoanNganHang", soTaiKhoanNganHangrut)
                    .setParameter("ID_NhanVien", idnhanvien)
                    .setParameter("SoTienGiaoDich", hieu)
                    .setParameter("NgayGiaoDich", day)
                    .setParameter("ThangGiaoDich", month)
                    .setParameter("NamGiaoDich", year)
                    .setParameter("ThoiGianGiaoDich", localDateTime)
                    .setParameter("NoiDungGiaoDich", a)
                    .setParameter("TrangThaiGiaoDich", 1)
                    .setParameter("TaiKhoanNguoiNhan_Gui", soTaiKhoanNganHangrut)
                    .setParameter("PhiGiaoDich", phi);
            query.executeUpdate();
            tx.commit();
            //clear session
            httpSession.removeAttribute("soTaiKhoan9");
            httpSession.removeAttribute("soDuTaiKhoan9");
            httpSession.removeAttribute("trangThaiTaiKhoan9");
            httpSession.removeAttribute("ngayMoTaiKhoan9");
            httpSession.removeAttribute("tenKhachHang9");
            messageSuccessTransactionMoney = "Rút tiền thành công!";
            return "redirect:/home/transaction_money";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            messageTransactionMoney = "Nạp tiền không thành công!";
            return "home/transaction_money";
        } finally {
            session.close();
        }
    }

//    @RequestMapping(value = "ttcn1")
//    public String Huy(HttpSession httpSession) {
//        // Xóa các session liên quan đến giao dịch
//        httpSession.removeAttribute("soTaiKhoan9");
//        httpSession.removeAttribute("soDuTaiKhoan9");
//        httpSession.removeAttribute("trangThaiTaiKhoan9");
//        httpSession.removeAttribute("ngayMoTaiKhoan9");
//        httpSession.removeAttribute("tenKhachHang9");
//        // Chuyển hướng về trang chủ
//        return "redirect:/home/transaction_money";
//    }
}
