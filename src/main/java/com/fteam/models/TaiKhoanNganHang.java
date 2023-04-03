/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author maluo
 */
@Entity
@Table(name = "TaiKhoanNganHang")
public class TaiKhoanNganHang {
    @Id
    @Column(name = "SoTaiKhoanNganHang")
    private Long soTaiKhoanNganHang;
    
    @Column(name = "SoDuTaiKhoan")
    private Long soDuTaiKhoan;
    
    @Column(name = "TrangThaiTaiKhoan")
    private Integer trangThaiTaiKhoan;
    
    @Column(name = "NgayMoTaiKhoan")
    private Date ngayMoTaiKhoan;
    
    @Column(name = "NgayDongTaiKhoan")
    private Date ngayDongTaiKhoan;
    
    @ManyToOne
    @JoinColumn(name = "ID_KhachHang")
    private KhachHang khachHang;
    
    // constructors, getters, setters, etc.

    public Long getSoTaiKhoanNganHang() {
        return soTaiKhoanNganHang;
    }

    public Long getSoDuTaiKhoan() {
        return soDuTaiKhoan;
    }

    public Integer getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public Date getNgayMoTaiKhoan() {
        return ngayMoTaiKhoan;
    }

    public Date getNgayDongTaiKhoan() {
        return ngayDongTaiKhoan;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setSoTaiKhoanNganHang(Long soTaiKhoanNganHang) {
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
    }

    public void setSoDuTaiKhoan(Long soDuTaiKhoan) {
        this.soDuTaiKhoan = soDuTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(Integer trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    public void setNgayMoTaiKhoan(Date ngayMoTaiKhoan) {
        this.ngayMoTaiKhoan = ngayMoTaiKhoan;
    }

    public void setNgayDongTaiKhoan(Date ngayDongTaiKhoan) {
        this.ngayDongTaiKhoan = ngayDongTaiKhoan;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public TaiKhoanNganHang() {
    }

    public TaiKhoanNganHang(Long soTaiKhoanNganHang, Long soDuTaiKhoan, int trangThaiTaiKhoan, Date ngayMoTaiKhoan, Date ngayDongTaiKhoan, KhachHang khachHang) {
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.soDuTaiKhoan = soDuTaiKhoan;
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
        this.ngayMoTaiKhoan = ngayMoTaiKhoan;
        this.ngayDongTaiKhoan = ngayDongTaiKhoan;
        this.khachHang = khachHang;
    }
    
}

