/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import java.util.ArrayList;
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

/**
 *
 * @author dinhp
 */
@Controller
public class CustomerController {

    @Autowired

    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "/customer_management")
    public String Customers(Model model) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM KhachHang";
        Query query = session.createQuery(hql);
        List<KhachHang> list = query.list();
        model.addAttribute("khachhangs", list);
        tx.commit();
        System.out.print(hql);
        session.close();
        return "customer_management";
    }
//    @Transactional
//    @RequestMapping(value = "/customer_management", method = RequestMethod.GET)
//    public String searchCustommer(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
//        String keyword = request.getParameter("searchcustomer");
//        if (keyword == null) {
//            keyword = "";
//        }
//        try ( Session session = sessionFactory.openSession()) {
//
//            String hql = "SELECT nv FROM KhachHang nv JOIN FETCH nv.chucVu WHERE lower(nv.tenKhachHang) LIKE :keyword";
//            Query query = session.createQuery(hql);
//            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
//            List<KhachHang> khachhangs = query.list();
//            List<KhachHang> searchResult = new ArrayList<>();
//            // Search for staff with matching name
//            for (KhachHang khachhang : khachhangs) {
//                if (khachhang.getTenKhachHang().toLowerCase().contains(keyword.toLowerCase())) {
//                    searchResult.add(khachhang);
//                }
//            }
//            if (searchResult.isEmpty()) {
//                httpSession.setAttribute("message", "Không tìm thấy khách hàng!");
//                return "staff_management";
//            } else {
//                model.addAttribute("khachhangs", searchResult);
//                return "staff_management";
//            }
//        } catch (Exception e) {
//            // Handle any exceptions that occur
//            e.printStackTrace();
//            httpSession.setAttribute("message", "Có lỗi khi tìm kiếm khách hàng!");
//            return "redirect:/customer_management";
//        }
//    }
    @RequestMapping(value = "/customer_detail")
    public String CustomerDetail(Model model) {
        return "customer_detail";
    }

}
