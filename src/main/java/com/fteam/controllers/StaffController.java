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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class StaffController {
    private int countLoadPage = 0;

    @Autowired

    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "staff_management", method = RequestMethod.GET)
    public String searchStaff(HttpSession httpSession, HttpServletRequest request, ModelMap model) {
        model.addAttribute("pageTitle", "Quản lý nhân viên");
        countLoadPage++;
        if(countLoadPage>2){
            httpSession.removeAttribute("messageStaff");
            countLoadPage=0;
        }
        
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
                model.addAttribute("messageStaff", "Không tìm thấy nhân viên!");
                return "home/staff_management";
            } else {
                model.addAttribute("nhanviens", searchResult);
                return "home/staff_management";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
            model.addAttribute("messageStaff", "Có lỗi khi tìm kiếm nhân viên!");
            return "home/staff_management";
        }
    }
    
    @Transactional
    @RequestMapping(value = "staff_management/delete", method = RequestMethod.POST)
    public String deleteStaff(@RequestParam("deleteStaffId") int staffId, HttpSession httpSession) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String updateQuery = "DELETE FROM NhanVien WHERE ID_NhanVien = :id";
            Query query = session.createQuery(updateQuery);
            query.setParameter("id", staffId);
            query.executeUpdate();
            System.out.print(staffId);
            tx.commit();
            httpSession.setAttribute("messageStaff", "Xóa Thành Công!");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            httpSession.setAttribute("messageStaff", "Không thể xóa tài khoản!");
        } finally {
            session.close();
        }
        return "redirect:/home/staff_management";
    }

    @RequestMapping(value = "/deleteStaffModal", method = RequestMethod.POST)
    public String deleteStaffModal(@RequestParam("id") int staffId, Model model) {
        model.addAttribute("staffId", staffId);
        return "deleteStaffModal";
    }

}
