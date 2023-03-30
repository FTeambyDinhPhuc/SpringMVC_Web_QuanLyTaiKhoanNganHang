/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.NhanVien;
import java.util.List;
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

/**
 *
 * @author dinhp
 */

@Controller
public class StaffController {

    @Autowired

    private SessionFactory sessionFactory;
    @Transactional
    @RequestMapping(value = "staff_management")
    public String Staffs(ModelMap model) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        String hql = "FROM NhanVien";
//        Query query = session.createQuery(hql);
//        List<NhanVien> list = query.list();
//        model.addAttribute("nhanviens", list);
//        tx.commit();
//        session.close();
        Session session = sessionFactory.openSession();
        String hql = "FROM NhanVien";
        Query query = session.createQuery(hql);
        List<NhanVien> list = query.list();
        model.addAttribute("nhanviens", list);
        return "staff_management";
    }

}
