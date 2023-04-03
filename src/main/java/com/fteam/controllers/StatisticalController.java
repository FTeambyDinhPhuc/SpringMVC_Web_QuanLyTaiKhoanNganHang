/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.NhanVien;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dinhp
 */
@Controller
@RequestMapping("/home/")
public class StatisticalController {

    private String messageStatistical = null;
    private String messageSuccessStatistical = null;
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "statistical", method = RequestMethod.GET)
    public String statistical(Model model, HttpSession httpSession) {
        model.addAttribute("pageTitle", "Thống kê");
        if (messageStatistical != null || messageSuccessStatistical != null) {
            model.addAttribute("messageStatistical", messageStatistical);
            model.addAttribute("messageSuccessStatistical", messageSuccessStatistical);
            messageStatistical = null;
            messageSuccessStatistical = null;
        }
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        String getnv = "FROM NhanVien";
        Query tongnv = session.createQuery(getnv);
        List<NhanVien> nhanviens = tongnv.list();
        int countNV = nhanviens.size();

        String getkh = "FROM KhachHang";
        Query tongkh = session.createQuery(getkh);
        List<KhachHang> khachhangs = tongkh.list();
        int countKH = khachhangs.size();

        String getThe = "FROM The";
        Query tongThe = session.createQuery(getThe);
        List<The> thes = tongThe.list();
        int countThe = thes.size();

        String getTien = "FROM TaiKhoanNganHang";
        Query truyVanTongTien = session.createQuery(getTien);
        List<TaiKhoanNganHang> taiKhoanNganHangs = truyVanTongTien.list();
        BigInteger tongTien = BigInteger.ZERO; // khởi tạo tổng số dư = 0
        for (TaiKhoanNganHang tk : taiKhoanNganHangs) {
            BigInteger soDu = BigInteger.valueOf(tk.getSoDuTaiKhoan()); // chuyển đổi kiểu dữ liệu Long sang BigInteger
            tongTien = tongTien.add(soDu);
        }

        httpSession.setAttribute("tongnhanvien", countNV);
        httpSession.setAttribute("tongkhachhang", countKH);
        httpSession.setAttribute("tongthe", countThe);
        httpSession.setAttribute("tongtien", tongTien);

        tx.commit();

        return "home/statistical";
    }
}
