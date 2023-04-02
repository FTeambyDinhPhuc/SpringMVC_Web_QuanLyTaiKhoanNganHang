/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

//    @RequestMapping(value = "bank_card_management")
//    public String BankCards(Model model) {
//        model.addAttribute("pageTitle", "Dịch vụ thẻ");
//        if (messageBankCard != null || messageSuccessBankCard != null) {
//            model.addAttribute("messageBankCard", messageBankCard);
//            model.addAttribute("messageSuccessBankCard", messageSuccessBankCard);
//            messageBankCard = null;
//            messageSuccessBankCard = null;
//        }
//        return "home/bank_card_management";
//    }
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
        try (Session session = sessionFactory.openSession()) {

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
                return "home/bank_card_management";
            } else {
//                model.addAttribute("messageBankCard", "Không tìm thấy tài khoản!");              
                return "home/bank_card_management";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
//            model.addAttribute("messageBankCard", "Có lỗi khi tìm kiếm khách hàng!");
            return "home/bank_card_management";
        }
    }
}
