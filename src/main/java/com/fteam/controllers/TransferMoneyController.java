/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.TaiKhoanNganHang;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class TransferMoneyController {

    private String messageTransferMoney = null;
    private String messageSuccessTransferMoney = null;
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "transfer_money")
    public String TransferMoney(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Chuyển khoản");
        String keyword = request.getParameter("searchBankAccount");
        String keyword1 = request.getParameter("searchBankAccount1");
        if (messageTransferMoney != null || messageSuccessTransferMoney != null) {
            model.addAttribute("messageTransferMoney", messageTransferMoney);
            model.addAttribute("messageSuccessTransferMoney", messageSuccessTransferMoney);
            messageTransferMoney = null;
            messageSuccessTransferMoney = null;
        }

        try ( Session session = sessionFactory.openSession()) {
            String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
            String hql1 = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword1";
            Query query = session.createQuery(hql);
            Query query1 = session.createQuery(hql1);
            query.setParameter("keyword", keyword);
            query1.setParameter("keyword1", keyword1);
            List<TaiKhoanNganHang> account = query.list();
            List<TaiKhoanNganHang> account1 = query1.list();

            boolean isAccount1Empty = account1.isEmpty();
            boolean isAccountEmpty = account.isEmpty();
            if (!isAccountEmpty) {
                TaiKhoanNganHang accountBank = account.get(0);
                model.addAttribute("acbank", accountBank);
                httpSession.setAttribute("soTaiKhoan", accountBank.getSoTaiKhoanNganHang());
                httpSession.setAttribute("soDuTaiKhoan", accountBank.getSoDuTaiKhoan());
                httpSession.setAttribute("trangThaiTaiKhoan", accountBank.getTrangThaiTaiKhoan());
                httpSession.setAttribute("ngayMoTaiKhoan", accountBank.getNgayMoTaiKhoan());
                return "home/transfer_money";
            } else if (!isAccount1Empty) {
                TaiKhoanNganHang accountBank1 = account1.get(0);
                model.addAttribute("acbank1", accountBank1);
                return "home/transfer_money";
            } else {
                return "home/transfer_money";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "home/transfer_money";
        }
    }

}
