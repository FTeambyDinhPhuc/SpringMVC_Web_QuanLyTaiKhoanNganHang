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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dinhp
 */
@Controller
public class AuthController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(ModelMap model) {
        model.addAttribute("NhanVien", new NhanVien());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model, @ModelAttribute("NhanVien") NhanVien NhanVien) {
        String hql = "FROM NhanVien u WHERE u.TenDangNhap = :TenDangNhap AND u.MatKhau = :MatKhau";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("TenDangNhap", NhanVien.getTenDangNhap());
        query.setParameter("MatKhau", NhanVien.getMatKhau());

        List<NhanVien> list = query.list();
        if (list.size() > 0) {
            // Login successful
            return "index";
        } else {
            // Login failed
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng !");
            return "login";
        }
    }

    @RequestMapping(value = "/register")
    public String Register(ModelMap model) {
        return "register";
    }
}
