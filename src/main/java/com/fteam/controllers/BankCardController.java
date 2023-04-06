/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.ChucVu;
import com.fteam.models.KhachHang;
import com.fteam.models.LoaiThe;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class BankCardController {

    private String messageBankCard = null;
    private String messageSuccessBankCard = null;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "bank_card_management", method = RequestMethod.GET)
    public String searchAccount(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Dịch vụ thẻ");
        String keyword = request.getParameter("searchAccount");
        if (messageBankCard != null || messageSuccessBankCard != null) {
            model.addAttribute("messageBankCard", messageBankCard);
            model.addAttribute("messageSuccessBankCard", messageSuccessBankCard);
            messageBankCard = null;
            messageSuccessBankCard = null;
        }
        if (keyword != null && !keyword.isEmpty()) {
            try ( Session session = sessionFactory.openSession()) {
                String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
                Query query = session.createQuery(hql);
                query.setParameter("keyword", keyword);
                TaiKhoanNganHang taiKhoanNganHang11 = (TaiKhoanNganHang) query.uniqueResult();
                if (taiKhoanNganHang11 == null) {
                    // Tài khoản không tồn tại
                    messageBankCard = "Tài khoản không tồn tại!";
                    return "redirect:/home/bank_card_management";
                }
                List<TaiKhoanNganHang> account = query.list();

                boolean isAccountEmpty = account.isEmpty();
                if (!isAccountEmpty) {
                    TaiKhoanNganHang accountBank = account.get(0);
                    httpSession.setAttribute("soTaiKhoan10", accountBank.getSoTaiKhoanNganHang());
                    httpSession.setAttribute("soDuTaiKhoan10", accountBank.getSoDuTaiKhoan());
                    httpSession.setAttribute("trangThaiTaiKhoan10", accountBank.getTrangThaiTaiKhoan());
                    httpSession.setAttribute("ngayMoTaiKhoan10", accountBank.getNgayMoTaiKhoan());

                    String checkpass = "SELECT kh FROM KhachHang kh JOIN TaiKhoanNganHang tknh ON kh.idKhachHang=tknh.khachHang WHERE tknh.soTaiKhoanNganHang = :stk";
                    Query checkp = session.createQuery(checkpass);
                    checkp.setParameter("stk", httpSession.getAttribute("soTaiKhoan10"));
                    List<KhachHang> khachhangs = checkp.list();
                    KhachHang khachHang = khachhangs.get(0);
                    httpSession.setAttribute("tenKhachHang10", khachHang.getTenKhachHang());
                    String the = "FROM The WHERE SoTaiKhoanNganHang=:keyword";
                    Query queryy = session.createQuery(the);
                    queryy.setParameter("keyword", keyword);
                    List<The> thes = queryy.list();
                    model.addAttribute("theBank", thes);
                    return "home/bank_card_management";
                }

            } catch (Exception e) {
                // Handle any exceptions that occur
                e.printStackTrace();
//            model.addAttribute("messageBankCard", "Có lỗi khi tìm kiếm khách hàng!");
                return "home/bank_card_management";
            }
        }
        return null;
    }

    @Transactional
    @RequestMapping(value = "bank_card_management/unlockbank", method = RequestMethod.POST)
    public String unlockcard(@RequestParam("laysothe") String sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            long bigNumber = Long.parseLong(sothe);
            tx = session.beginTransaction();
            String unlockcard = "UPDATE The SET TrangThaiThe=1 WHERE SoThe=:id";
            Query query = session.createQuery(unlockcard);
            query.setParameter("id", bigNumber);
            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Mở khóa thành công!";
            httpSession.removeAttribute("soTaiKhoan10");
            httpSession.removeAttribute("soDuTaiKhoan10");
            httpSession.removeAttribute("trangThaiTaiKhoan10");
            httpSession.removeAttribute("ngayMoTaiKhoan10");
            httpSession.removeAttribute("tenKhachHang10");
            return "redirect:/home/bank_card_management";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageBankCard = "Mở khóa không thành công!";
        } finally {
            session.close();
        }
        return "redirect:/home/bank_card_management";
    }

    @Transactional
    @RequestMapping(value = "bank_card_management/lockbank", method = RequestMethod.POST)
    public String lockcard(@RequestParam("laysothelock") String sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            long lock = Long.parseLong(sothe);
            tx = session.beginTransaction();
            String lockbank = "UPDATE The SET TrangThaiThe=0 WHERE SoThe=:id";
            Query query = session.createQuery(lockbank);
            query.setParameter("id", lock);
            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Khóa thành công!";
            httpSession.removeAttribute("soTaiKhoan10");
            httpSession.removeAttribute("soDuTaiKhoan10");
            httpSession.removeAttribute("trangThaiTaiKhoan10");
            httpSession.removeAttribute("ngayMoTaiKhoan10");
            httpSession.removeAttribute("tenKhachHang10");
            return "redirect:/home/bank_card_management";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageBankCard = "Khóa không thành công!";
        } finally {
            session.close();
        }
        return "redirect:/home/bank_card_management";
    }

    @Transactional
    @RequestMapping(value = "bank_card_management/extend", method = RequestMethod.POST)
    public String extend(@RequestParam("laysothegiahan") String sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            long giahanthe = Long.parseLong(sothe);
            tx = session.beginTransaction();
            String giahan = "FROM The WHERE SoThe=:id";
            Query queryy = session.createQuery(giahan);
            queryy.setParameter("id", giahanthe);
            List<The> laythe = queryy.list();
            The The = laythe.get(0);
            Date ngayhethan = The.getNgayHetHan();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayhethan);
            calendar.add(Calendar.YEAR, 5);
            Date ngayhethanmoi = calendar.getTime();

            String lockbank = "UPDATE The SET NgayhetHan=:ngaymoi WHERE SoThe=:id";
            Query query = session.createQuery(lockbank);
            query.setParameter("id", giahanthe);
            query.setParameter("ngaymoi", ngayhethanmoi);

            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Gia han thành công!";
            httpSession.removeAttribute("soTaiKhoan10");
            httpSession.removeAttribute("soDuTaiKhoan10");
            httpSession.removeAttribute("trangThaiTaiKhoan10");
            httpSession.removeAttribute("ngayMoTaiKhoan10");
            httpSession.removeAttribute("tenKhachHang10");
            return "redirect:/home/bank_card_management";
        } catch (NumberFormatException e) {
            if (tx != null) {
                tx.rollback();
            }
            messageBankCard = "Gia han không thành công!";
        } finally {
            session.close();
        }
        return "home/bank_card_management";
    }

    @Transactional
    @RequestMapping(value = "/openBankCard", method = RequestMethod.POST)
    public String openBankCard(@RequestParam("cardtype") String cardType, HttpSession httpSession) {
        // Xử lý nghiệp vụ mở thẻ ngân hàng ở đây
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        long soTaiKhoanNganHang = (long) httpSession.getAttribute("soTaiKhoan10");
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.YEAR, 5);
        Date fiveYearLater = calendar.getTime();
        Random rand = new Random();
        int loaithe = 0;
        int upperbound = 999;
        int CVV = rand.nextInt(upperbound);
        int trangThaiThe = 0;
        long sothe = (long) (97044 * Math.pow(10, 11) + rand.nextInt((int) Math.pow(10, 11)));
        if ("gn".equals(cardType)) {
            loaithe = 1;
        } else if ("td".equals(cardType)) {
            loaithe = 2;
        }
        String the = "FROM The WHERE SoTaiKhoanNganHang=:soTaiKhoanNganHang";
        Query queryy = session.createQuery(the);
        queryy.setParameter("soTaiKhoanNganHang", soTaiKhoanNganHang);
        List<The> cards = queryy.list();
        LoaiThe loaiThe = session.get(LoaiThe.class, loaithe);
        boolean isCardTypeExist = false;
        for (The card : cards) {
            if (card.getLoaiThe() == loaiThe) {
                isCardTypeExist = true;
                break;
            }
        }
        if (isCardTypeExist) {
            messageBankCard = "Loại thẻ đã tồn tại. Xin thử lại sau!";
            return "redirect:/home/bank_card_management";
        } else {
            String insertQuery = "INSERT INTO The(SoThe, NgayCapThe,NgayHetHan, CVV, TrangThaiThe, SoTaiKhoanNganHang,ID_LoaiThe) VALUES( :SoThe, :NgayCapThe,:NgayHetHan ,:CVV, :TrangThaiThe, :SoTaiKhoanNganHang, :ID_LoaiThe)";
            Query updateNhanVienQuery = session.createSQLQuery(insertQuery)
                    .setParameter("SoThe", sothe)
                    .setParameter("NgayCapThe", now)
                    .setParameter("NgayHetHan", fiveYearLater)
                    .setParameter("CVV", CVV)
                    .setParameter("TrangThaiThe", trangThaiThe)
                    .setParameter("SoTaiKhoanNganHang", soTaiKhoanNganHang)
                    .setParameter("ID_LoaiThe", loaithe);
            tx = session.beginTransaction();
            updateNhanVienQuery.executeUpdate();
            tx.commit();
            httpSession.removeAttribute(
                    "soTaiKhoan10");
            httpSession.removeAttribute(
                    "soDuTaiKhoan10");
            httpSession.removeAttribute(
                    "trangThaiTaiKhoan10");
            httpSession.removeAttribute(
                    "ngayMoTaiKhoan10");
            httpSession.removeAttribute(
                    "tenKhachHang10");
            messageSuccessBankCard = "Mở thẻ thành công!";
            // Redirect về trang chủ của ngân hàng

        }

        return "redirect:/home/bank_card_management";
    }

}
