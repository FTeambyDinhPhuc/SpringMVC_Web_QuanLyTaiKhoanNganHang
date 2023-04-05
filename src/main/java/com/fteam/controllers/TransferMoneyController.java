/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.TaiKhoanNganHang;
import static java.lang.Integer.parseInt;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class TransferMoneyController {

    private String messageTransferMoney = null;
    private String messageSuccessTransferMoney = null;
    private String messageTransferMoneyAcc1 = null;
    private String messageTransferMoneyAcc2 = null;
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "transfer_money")
    public String TransferMoney(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Chuyển khoản");
        String keyword = request.getParameter("searchBankAccount");
        String keyword1 = request.getParameter("searchBankAccount1");
        if (messageTransferMoney != null || messageSuccessTransferMoney != null || messageTransferMoneyAcc1 != null || messageTransferMoneyAcc2 != null) {
            model.addAttribute("messageTransferMoney", messageTransferMoney);
            model.addAttribute("messageSuccessTransferMoney", messageSuccessTransferMoney);
            model.addAttribute("messageTransferMoneyAcc1", messageTransferMoneyAcc1);
            model.addAttribute("messageTransferMoneyAcc2", messageTransferMoneyAcc2);
            messageTransferMoney = null;
            messageSuccessTransferMoney = null;
            messageTransferMoneyAcc1 = null;
            messageTransferMoneyAcc2 = null;
        }

        if (keyword != null && !keyword.isEmpty()) {
            try ( Session session = sessionFactory.openSession()) {
                String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
                Query query = session.createQuery(hql);
                query.setParameter("keyword", keyword);
                TaiKhoanNganHang taiKhoanNganHang11 = (TaiKhoanNganHang) query.uniqueResult();
                if (taiKhoanNganHang11 == null) {
                    // Tài khoản không tồn tại
                    messageTransferMoneyAcc1 = "Tài khoản không tồn tại!";
                    return "redirect:/home/transfer_money";
                }
                List<TaiKhoanNganHang> account = query.list();

                boolean isAccountEmpty = account.isEmpty();
                if (!isAccountEmpty) {
                    TaiKhoanNganHang accountBank = account.get(0);
                    model.addAttribute("acbank", accountBank);
                    httpSession.setAttribute("soTaiKhoan", accountBank.getSoTaiKhoanNganHang());
                    httpSession.setAttribute("soDuTaiKhoan", accountBank.getSoDuTaiKhoan());
                    httpSession.setAttribute("trangThaiTaiKhoan", accountBank.getTrangThaiTaiKhoan());
                    httpSession.setAttribute("ngayMoTaiKhoan", accountBank.getNgayMoTaiKhoan());

                    String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";
                    Query checkp = session.createQuery(checkpass);
                    checkp.setParameter("stk", httpSession.getAttribute("soTaiKhoan"));
                    List<KhachHang> khachhangs = checkp.list();
                    KhachHang khachHang = khachhangs.get(0);
                    httpSession.setAttribute("tenKhachHang1", khachHang.getTenKhachHang());
                    return "home/transfer_money";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (keyword1 != null && !keyword1.isEmpty()) {
            try ( Session session = sessionFactory.openSession()) {
                String hql1 = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword1";
                Query query1 = session.createQuery(hql1);
                query1.setParameter("keyword1", keyword1);
                TaiKhoanNganHang taiKhoanNganHang10 = (TaiKhoanNganHang) query1.uniqueResult();
                if (taiKhoanNganHang10 == null) {
                    // Tài khoản không tồn tại
                    messageTransferMoneyAcc2 = "Tài khoản không tồn tại!";
                    return "redirect:/home/transfer_money";
                }
                List<TaiKhoanNganHang> account1 = query1.list();
                boolean isAccount1Empty = account1.isEmpty();
                if (!isAccount1Empty) {
                    TaiKhoanNganHang accountBank1 = account1.get(0);
                    model.addAttribute("acbank1", accountBank1);
                    httpSession.setAttribute("soTaiKhoan1", accountBank1.getSoTaiKhoanNganHang());
                    httpSession.setAttribute("soDuTaiKhoan1", accountBank1.getSoDuTaiKhoan());
                    httpSession.setAttribute("trangThaiTaiKhoan1", accountBank1.getTrangThaiTaiKhoan());
                    httpSession.setAttribute("ngayMoTaiKhoan1", accountBank1.getNgayMoTaiKhoan());
                    String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk1";
                    Query checkp = session.createQuery(checkpass);
                    checkp.setParameter("stk1", httpSession.getAttribute("soTaiKhoan1"));
                    List<KhachHang> khachhangs = checkp.list();
                    KhachHang khachHang = khachhangs.get(0);
                    httpSession.setAttribute("tenKhachHang2", khachHang.getTenKhachHang());
                    return "home/transfer_money";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "home/transfer_money";
    }

    @Transactional
    @RequestMapping(value = "transferMoneyModal", method = RequestMethod.POST)
    public String chuyenkhoan(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        if (httpSession.getAttribute("soTaiKhoan") == null) {
            messageTransferMoneyAcc1 = "Thông tin chuyển khoản trống";
            return "redirect:/home/transfer_money";
        } else if (httpSession.getAttribute("soTaiKhoan1") == null) {
            messageTransferMoneyAcc2 = "Thông tin chuyển khoản trống";
            return "redirect:/home/transfer_money";
        }
        Object trangThaiTaiKhoan1 = httpSession.getAttribute("trangThaiTaiKhoan");
        Object trangThaiTaiKhoan2 = httpSession.getAttribute("trangThaiTaiKhoan1");
        if (trangThaiTaiKhoan1 == null || Integer.parseInt(trangThaiTaiKhoan1.toString()) == 0) {
            messageTransferMoneyAcc1 = "Tài khoản bị khóa không thể giao dịch";
            return "redirect:/home/transfer_money";
        } else if (trangThaiTaiKhoan2 == null || Integer.parseInt(trangThaiTaiKhoan2.toString()) == 0) {
            messageTransferMoneyAcc2 = "Tài khoản bị khóa không thể giao dịch";
            return "redirect:/home/transfer_money";
        }

        String tenNguoiGui = request.getParameter("tenNguoiGui");
        String soTaiKhoanNganHangGui = request.getParameter("soTaiKhoanNganHangGui");
        String soTaiKhoanNganHangNhan = request.getParameter("soTaiKhoanNganHangNhan");
        if (soTaiKhoanNganHangGui.equals(soTaiKhoanNganHangNhan)) {
            messageTransferMoney = "Hai tài khoản trùng nhau!";
            return "redirect:/home/transfer_money";
        }
        long soTaiKhoanNganHangGuiLong = Long.parseLong(soTaiKhoanNganHangGui);
        long soTaiKhoanNganHangNhanLong = Long.parseLong(soTaiKhoanNganHangNhan);

        String tenNguoiNhan = request.getParameter("tenNguoiNhan");
        String phiGiaoDich = request.getParameter("phiGiaoDich");
        double phiGiaoDichDouble = Double.parseDouble(phiGiaoDich);
        String canCuoc = request.getParameter("canCuoc");
        String noiDungChuyenKhoan = request.getParameter("noiDungChuyenKhoan");
        String soTienGiaoDichStr = request.getParameter("soTienGiaoDich");
        int soTienGiaoDich = 0;
        if (soTienGiaoDichStr != null && !soTienGiaoDichStr.equals("")) {
            soTienGiaoDich = Integer.parseInt(soTienGiaoDichStr);
        }

        String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";
        Query checkp = session.createQuery(checkpass);
        checkp.setParameter("stk", soTaiKhoanNganHangGuiLong);
        List<KhachHang> khachhangs = checkp.list();
        KhachHang khachHang = khachhangs.get(0);
        String cCccd = khachHang.getCccd();
        if (cCccd == null ? canCuoc != null : !cCccd.equals(canCuoc)) {
            messageTransferMoney = "Căn cước công dân không trùng khớp!";
            return "redirect:/home/transfer_money";
        }
        String checkpass3 = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk3";
        Query checkp3 = session.createQuery(checkpass3);
        checkp3.setParameter("stk3", soTaiKhoanNganHangNhanLong);
        List<KhachHang> khachhangs3 = checkp3.list();
        KhachHang khachHang3 = khachhangs3.get(0);

        //Lấy thông tin tài khoản của khách hàng 1
        String checkpass1 = "SELECT tknh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk1";
        Query checkp1 = session.createQuery(checkpass1);
        checkp1.setParameter("stk1", soTaiKhoanNganHangGuiLong);
        List<TaiKhoanNganHang> taiKhoanNganHangs = checkp1.list();
        TaiKhoanNganHang taiKhoanNganHang = taiKhoanNganHangs.get(0);
        Long tk = taiKhoanNganHang.getSoDuTaiKhoan();
        if (tk < soTienGiaoDich) {
            messageTransferMoney = "Số dư không đủ!";
            return "redirect:/home/transfer_money";
        }

        //Lấy thông tin tài khoản ngân hàng của khách hàng 2
        String checkpass2 = "SELECT tknh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk2";
        Query checkp2 = session.createQuery(checkpass2);
        checkp2.setParameter("stk2", soTaiKhoanNganHangNhanLong);
        List<TaiKhoanNganHang> taiKhoanNganHangs2 = checkp2.list();
        TaiKhoanNganHang taiKhoanNganHang2 = taiKhoanNganHangs2.get(0);
        Long tk2 = taiKhoanNganHang2.getSoDuTaiKhoan();
        long tong = 0;
        tong = (long) tk2 + soTienGiaoDich;
        long hieu = 0;
        hieu = (long) (tk - soTienGiaoDich);
        int idnhanvien = (int) httpSession.getAttribute("idNhanVien");
        try {
            tx = session.beginTransaction();
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = LocalDate.now().getYear();
            Date now = new Date();
            long timestamp = now.getTime();
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            String editQuery = "UPDATE TaiKhoanNganHang t SET t.soDuTaiKhoan = :sodu WHERE t.khachHang.id = :id and t.soTaiKhoanNganHang=:stk5";
            Query updateNhanVienQuery = session.createQuery(editQuery)
                    .setParameter("sodu", tong)
                    .setParameter("stk5", soTaiKhoanNganHangNhanLong)
                    .setParameter("id", khachHang3.getIdKhachHang());
            updateNhanVienQuery.executeUpdate();

            String editQuery1 = "UPDATE TaiKhoanNganHang t SET t.soDuTaiKhoan = :sodu1 WHERE t.khachHang.id = :id1 and t.soTaiKhoanNganHang=:stk6";
            Query updateNhanVienQuery1 = session.createQuery(editQuery1)
                    .setParameter("sodu1", hieu)
                    .setParameter("stk6", soTaiKhoanNganHangGuiLong)
                    .setParameter("id1", khachHang.getIdKhachHang());
            updateNhanVienQuery1.executeUpdate();

            String insertQuery = "INSERT INTO PhieuGiaoDich(ID_GiaoDich, SoTaiKhoanNganHang, "
                    + "ID_NhanVien, SoTienGiaoDich, "
                    + "NgayGiaoDich, ThangGiaoDich, NamGiaoDich, ThoiGianGiaoDich, "
                    + "NoiDungGiaoDich, TrangThaiGiaoDich,TaiKhoanNguoiNhan_Gui,PhiGiaoDich) "
                    + "VALUES (:ID_GiaoDich, :SoTaiKhoanNganHang, :ID_NhanVien, :SoTienGiaoDich, :NgayGiaoDich, "
                    + ":ThangGiaoDich, :NamGiaoDich, :ThoiGianGiaoDich, :NoiDungGiaoDich,"
                    + " :TrangThaiGiaoDich, :TaiKhoanNguoiNhan_Gui, :PhiGiaoDich)";
            Query query = session.createSQLQuery(insertQuery)
                    .setParameter("ID_GiaoDich", 3)
                    .setParameter("SoTaiKhoanNganHang", soTaiKhoanNganHangGuiLong)
                    .setParameter("ID_NhanVien", idnhanvien)
                    .setParameter("SoTienGiaoDich", soTienGiaoDich)
                    .setParameter("NgayGiaoDich", day)
                    .setParameter("ThangGiaoDich", month)
                    .setParameter("NamGiaoDich", year)
                    .setParameter("ThoiGianGiaoDich", localDateTime)
                    .setParameter("NoiDungGiaoDich", noiDungChuyenKhoan)
                    .setParameter("TrangThaiGiaoDich", 1)
                    .setParameter("TaiKhoanNguoiNhan_Gui", soTaiKhoanNganHangNhanLong)
                    .setParameter("PhiGiaoDich", phiGiaoDichDouble);
            query.executeUpdate();
            tx.commit();

            // Clear session
            httpSession.removeAttribute("soTaiKhoan");
            httpSession.removeAttribute("soDuTaiKhoan");
            httpSession.removeAttribute("trangThaiTaiKhoan");
            httpSession.removeAttribute("ngayMoTaiKhoan");
            httpSession.removeAttribute("soTaiKhoan1");
            httpSession.removeAttribute("soDuTaiKhoan1");
            httpSession.removeAttribute("trangThaiTaiKhoan1");
            httpSession.removeAttribute("ngayMoTaiKhoan1");
            httpSession.removeAttribute("tenKhachHang1");
            httpSession.removeAttribute("tenKhachHang2");
            // Set success message
            messageSuccessTransferMoney = "Chuyển tiền thành công!";
            return "redirect:/home/transfer_money";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home/transfer_money";
        }

    }

    @RequestMapping(value = "ttcn")
    public String cancel(HttpSession httpSession) {
        // Xóa các session liên quan đến giao dịch
        httpSession.removeAttribute("soTaiKhoan");
        httpSession.removeAttribute("soDuTaiKhoan");
        httpSession.removeAttribute("trangThaiTaiKhoan");
        httpSession.removeAttribute("ngayMoTaiKhoan");
        httpSession.removeAttribute("soTaiKhoan1");
        httpSession.removeAttribute("soDuTaiKhoan1");
        httpSession.removeAttribute("trangThaiTaiKhoan1");
        httpSession.removeAttribute("ngayMoTaiKhoan1");
        httpSession.removeAttribute("tenKhachHang1");
        httpSession.removeAttribute("tenKhachHang2");

        // Chuyển hướng về trang chủ
        return "redirect:/home/transfer_money";
    }
}
