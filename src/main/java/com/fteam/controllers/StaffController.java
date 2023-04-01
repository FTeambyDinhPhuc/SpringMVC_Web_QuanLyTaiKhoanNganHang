/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.NhanVien;
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
@RequestMapping("/home/")
public class StaffController {

    @Autowired

    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "staff_management", method = RequestMethod.GET)
    public String searchStaff(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
        String keyword = request.getParameter("searchstaff");
        if (keyword == null) {
            keyword = "";
        }
        try ( Session session = sessionFactory.openSession()) {

            String hql = "SELECT nv FROM NhanVien nv JOIN FETCH nv.chucVu WHERE lower(nv.tenNhanVien) LIKE :keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            List<NhanVien> nhanviens = query.list();
            List<NhanVien> searchResult = new ArrayList<>();
            // Search for staff with matching name
            for (NhanVien nhanVien : nhanviens) {
                if (nhanVien.getTenNhanVien().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResult.add(nhanVien);
                }
            }
            if (searchResult.isEmpty()) {
                httpSession.setAttribute("message", "Không tìm thấy nhân viên!");
                return "home/staff_management";
            } else {
                model.addAttribute("nhanviens", searchResult);
                return "home/staff_management";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
            httpSession.setAttribute("message", "Có lỗi khi tìm kiếm nhân viên!");
            return "home/staff_management";
        }
    }

}
