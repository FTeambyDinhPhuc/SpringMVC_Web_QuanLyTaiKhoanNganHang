/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import com.fteam.models.KhachHang;
import com.fteam.models.PhieuGiaoDich;
import com.fteam.models.TaiKhoanNganHang;
import com.fteam.models.The;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class TransactionHistoryController {

    private String messageTransactionHistory = null;
    private String messageSuccessTransactionHistory = null;
    private List<PhieuGiaoDich> saoke = null;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @RequestMapping(value = "transaction_history", method = RequestMethod.GET)
    public String TransactionHistory(ModelMap model, HttpSession httpSession, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Thống kê giao dịch khách hàng");
        String keyword = request.getParameter("searchBankAccount");
        if (messageTransactionHistory != null || messageSuccessTransactionHistory != null || saoke != null) {
            model.addAttribute("messageTransactionHistory", messageTransactionHistory);
            model.addAttribute("messageSuccessTransactionHistory", messageSuccessTransactionHistory);
            model.addAttribute("saoke", saoke);
            saoke = null;
            messageTransactionHistory = null;
            messageSuccessTransactionHistory = null;
        }
        if (keyword == null || keyword.matches(".*[^0-9].*")) {
            return "home/transaction_history";
        }
        Session session = sessionFactory.openSession();
        String hql = "FROM TaiKhoanNganHang WHERE SoTaiKhoanNganHang=:keyword";
        Query query = session.createQuery(hql);
        query.setParameter("keyword", keyword);

        List<TaiKhoanNganHang> account = query.list();
        if (!account.isEmpty()) {
            TaiKhoanNganHang accountBank = account.get(0);
//                KhachHang idkh = accountBank.getKhachHang();
            long key = Long.parseLong(keyword);
//                String khachf = "FROM KhachHang WHERE ID_KachHang=:id";
            String khach = "SELECT kh FROM KhachHang kh LEFT JOIN TaiKhoanNganHang tknh "
                    + "ON kh.idKhachHang=tknh.khachHang "
                    + "WHERE tknh.soTaiKhoanNganHang = :id";
            Query queryy = session.createQuery(khach);
            queryy.setParameter("id", key);
            List<KhachHang> khachhangs = queryy.list();
            KhachHang kh = khachhangs.get(0);

            model.addAttribute("khang", kh);
            model.addAttribute("ttthe", accountBank);
            return "home/transaction_history";
        } else {
            messageTransactionHistory = "Không tìm thấy tài khoản!";
            return "home/transaction_history";
        }
    }

    @RequestMapping(value = "transaction_history/loc", method = RequestMethod.POST)
    public String searchTransactionHistory(HttpServletRequest request, HttpSession httpSession, ModelMap model) {
        Session session = sessionFactory.openSession();
        String loc = request.getParameter("loc");
        if (loc == null) {
            return "redirect:/home/transaction_history";
        }
        String[] parts = loc.split("-");
        String nam = parts[0];
        String thang = parts[1].startsWith("0") ? parts[1].substring(1) : parts[1];
        String ngay = parts[2].startsWith("0") ? parts[2].substring(1) : parts[2];
//        int nam = Integer.parseInt(a);
//        int thang = Integer.parseInt(b);
//        int ngay = Integer.parseInt(c);
        String idKhachHang = request.getParameter("idkhach");
        try {

//            long id = Long.parseLong(idKhachHang);
            String phieugd = "FROM PhieuGiaoDich WHERE SoTaiKhoanNganHang=:id AND NgayGiaoDich=:ngay "
                    + "AND ThangGiaoDich=:thang AND NamGiaoDich=:nam";
            Query queryyy = session.createQuery(phieugd);
            queryyy.setParameter("id", idKhachHang);
            queryyy.setParameter("nam", nam);
            queryyy.setParameter("thang", thang);
            queryyy.setParameter("ngay", ngay);
            List<PhieuGiaoDich> rut = queryyy.list();
            saoke = rut;
      
            if (!saoke.isEmpty()) {
                messageSuccessTransactionHistory = "Tìm thấy giao dịch!";
                return "redirect:/home/transaction_history";
            } else {
                messageTransactionHistory = "Không tìm thấy giao dịch trong thời gian này!";
                return "redirect:/home/transaction_history";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home/transaction_history";
        }
    }

//    @RequestMapping(value = "transaction_history/xuatexcel", method = RequestMethod.POST)
//    public void searchTransactionHistory(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
//        Session session = sessionFactory.openSession();
//        String idKhachHang = request.getParameter("sotk");
//        try {
//            String phieugd = "FROM PhieuGiaoDich WHERE SoTaiKhoanNganHang=:id";
//            Query queryyy = session.createQuery(phieugd);
//            queryyy.setParameter("id", idKhachHang);
//
//            List<PhieuGiaoDich> list = queryyy.list();
//
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("SAO KE");
//            // Tạo một header row
//            XSSFRow headerRow = sheet.createRow(0);
//            // Tạo các cell cho header row
//            headerRow.createCell(0).setCellValue("ID");
//            headerRow.createCell(1).setCellValue("LOẠI GIAO DỊCH");
//            headerRow.createCell(2).setCellValue("NGƯỜI GỬI/NHẬN");
//            headerRow.createCell(3).setCellValue("SỐ TIỀN");
//            headerRow.createCell(4).setCellValue("NGÀY GIAO DỊCH");
//            headerRow.createCell(5).setCellValue("NỘI DUNG GIAO DỊCH");
//            headerRow.createCell(6).setCellValue("SỐ TÀI KHOẢN NGÂN HÀNG");
//            int rowNum = 1;
//            // Lặp qua danh sách phiếu giao dịch và tạo các row tương ứng
//            for (PhieuGiaoDich pgd : list) {
//                XSSFRow row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(pgd.getId());
//                row.createCell(1).setCellValue(pgd.getGiaoDich().getTenGiaoDich());
//                row.createCell(2).setCellValue(pgd.getTaiKhoanNguoiNhan_Gui().getTenTaiKhoan());
//                row.createCell(3).setCellValue(pgd.getSoTienGiaoDich());
//                row.createCell(4).setCellValue(String.format("%02d/%02d/%04d", pgd.getNgayGiaoDich(), pgd.getThangGiaoDich(), pgd.getNamGiaoDich()));
//                row.createCell(5).setCellValue(pgd.getNoiDungGiaoDich());
//                row.createCell(6).setCellValue(pgd.getTaiKhoanNganHang().getSoTaiKhoan());
//            }
//            // Lưu workbook vào file
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "attachment; filename=\"saoke.xlsx\"");
//            workbook.write(response.getOutputStream());
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "redirect:/home/transaction_history";
//        }
}
