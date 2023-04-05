/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import java.util.ArrayList;
import java.util.Calendar;
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
        try ( Session session = sessionFactory.openSession()) {
            String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", keyword);

            List<TaiKhoanNganHang> account = query.list();
            if (!account.isEmpty()) {
                TaiKhoanNganHang accountBank = account.get(0);

                String the = "FROM The WHERE SoTaiKhoanNganHang=:keyword";
                Query queryy = session.createQuery(the);
                queryy.setParameter("keyword", keyword);
                List<The> thes = queryy.list();

                model.addAttribute("theBank", thes);
                model.addAttribute("accountBank", accountBank);
                messageBankCard = null;
                return "home/bank_card_management";
            } else {
                messageBankCard = "Không tìm thấy tài khoản!";
                return "home/bank_card_management";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
//            model.addAttribute("messageBankCard", "Có lỗi khi tìm kiếm khách hàng!");
            return "home/bank_card_management";
        }
    }

    @Transactional
    @RequestMapping(value = "bank_card_management/unlockbank", method = RequestMethod.POST)
    public String unlockcard(@RequestParam("laysothe") int sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String unlockcard = "UPDATE The SET TrangThaiThe=1 WHERE SoThe=:id";
            Query query = session.createQuery(unlockcard);
            query.setParameter("id", sothe);
            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Mở khóa thành công!";
//            return "redirect:/home/bank_card_management";
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
    public String lockcard(@RequestParam("laysothelock") int sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String lockbank = "UPDATE The SET TrangThaiThe=0 WHERE SoThe=:id";
            Query query = session.createQuery(lockbank);
            query.setParameter("id", sothe);
            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Khóa thành công!";
//            return "redirect:/home/bank_card_management";
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
    public String extend(@RequestParam("laysothegiahan") int sothe, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String giahan = "FROM The WHERE SoThe=:id";
            Query queryy = session.createQuery(giahan);
            queryy.setParameter("id", sothe);
            List<The> laythe = queryy.list();
            The The = laythe.get(0);
            Date ngayhethan = The.getNgayHetHan();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayhethan);
            calendar.add(Calendar.YEAR, 5);
            Date ngayhethanmoi = calendar.getTime();

            String lockbank = "UPDATE The SET NgayhetHan=:ngaymoi WHERE SoThe=:id";
            Query query = session.createQuery(lockbank);
            query.setParameter("id", sothe);
            query.setParameter("ngaymoi", ngayhethanmoi);

            query.executeUpdate();
            tx.commit();
            messageSuccessBankCard = "Khóa thành công!";
//            return "redirect:/home/bank_card_management";
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            messageBankCard = "Khóa không thành công!";
        } finally {
            session.close();
        }
        return "rhome/bank_card_management";
    }
}
