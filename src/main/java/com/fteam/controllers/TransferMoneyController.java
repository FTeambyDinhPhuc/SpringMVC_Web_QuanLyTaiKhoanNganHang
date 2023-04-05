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
        
        httpSession.getAttribute("tenKhachHang2");
        String idnhanvien = request.getParameter("idnhanvien");
        try {
            tx = session.beginTransaction();
            if (httpSession.getAttribute("soTaiKhoan")==null || httpSession.getAttribute("soTaiKhoan1")==null) {
                messageTransferMoney = "Thông tin chuyển khoản trống";
                return "redirect:/home/transfer_money";
            }

            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = LocalDate.now().getYear();
            Date now = new Date();
            long timestamp = now.getTime();
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        } catch (Exception e) {
                            
                return "redirect:/home/transfer_money";
        }
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
        model.addAttribute("messageSuccessTransferMoney", "Chuyển tiền thành công!");

        return "redirect:/home/transfer_money";
    }

}
