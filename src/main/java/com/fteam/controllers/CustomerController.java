/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
        session.close();
        return "customer_management";
    }

    @RequestMapping(value = "/customer_detail")
    public String CustomerDetail(Model model) {
        return "customer_detail";
    }

}
