/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.NhanVien;
import com.fteam.models.PhieuGiaoDich;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
    public String statistical(Model model, HttpSession httpSession, HttpServletRequest request) {
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

        String keyword = request.getParameter("loc");
        
        if(keyword==null){
            return "home/statistical";
        }
        String[] parts = keyword.split("-");
        String a = parts[0];
        String b = parts[1];
        String c = parts[2];
        tx.commit();
        try {

            String naptien = "FROM PhieuGiaoDich WHERE ID_GiaoDich=2 AND NgayGiaoDich=:ngay AND ThangGiaoDich=:thang AND NamGiaoDich=:nam";
            Query query = session.createQuery(naptien);
            query.setParameter("nam", a);
            query.setParameter("thang", b);
            query.setParameter("ngay", c);
            List<PhieuGiaoDich> nap = query.list();
            int sizenap = nap.size();

            String ruttien = "FROM PhieuGiaoDich WHERE ID_GiaoDich=1 AND NgayGiaoDich=:ngay AND ThangGiaoDich=:thang AND NamGiaoDich=:nam";
            Query queryy = session.createQuery(ruttien);
            queryy.setParameter("nam", a);
            queryy.setParameter("thang", b);
            queryy.setParameter("ngay", c);
            List<PhieuGiaoDich> rut = queryy.list();
            int sizerut = rut.size();            

            if (!nap.isEmpty() || !rut.isEmpty()) {

                BigInteger napt = BigInteger.ZERO; // khởi tạo tổng số dư = 0
                for (PhieuGiaoDich gd : nap) {
                    BigInteger nt = BigInteger.valueOf(gd.getSoTienGiaoDich()); // chuyển đổi kiểu dữ liệu Long sang BigInteger
                    napt = napt.add(nt);
                }
                BigInteger rutt = BigInteger.ZERO; // khởi tạo tổng số dư = 0
                for (PhieuGiaoDich ruttie : rut) {
                    BigInteger rt = BigInteger.valueOf(ruttie.getSoTienGiaoDich()); // chuyển đổi kiểu dữ liệu Long sang BigInteger
                    rutt = rutt.add(rt);
                }
                model.addAttribute("ngay", keyword);
                model.addAttribute("naptien", napt);
                model.addAttribute("ruttien", rutt);
                model.addAttribute("soluonggiaodich", sizenap+sizerut);
                return "home/statistical";
            } else {
                messageStatistical = "Không tìm thấy giao dịch trong thời gian này!";
                return "home/statistical";
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
//            model.addAttribute("messageBankCard", "Có lỗi khi tìm kiếm khách hàng!");
            return "home/bank_card_management";
        }

    }
}
