/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author maluo
 */
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_KhachHang")
    private int idKhachHang;
 
    @Column(name = "TenKhachHang")
    private String tenKhachHang;
 
    @Column(name = "NgaySinhKH")
    private Date ngaySinhKH;
 
    @Column(name = "GioiTinh")
    private boolean gioiTinh;
 
    @Column(name = "DiaChiKH")
    private String diaChiKH;
 
    @Column(name = "EmailKH")
    private String emailKH;
 
    @Column(name = "CCCD")
    private String cccd;
 
    @Column(name = "NgheNghiep")
    private String ngheNghiep;
 
    @Column(name = "SoDienThoai")
    private String soDienThoai;

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public Date getNgaySinhKH() {
        return ngaySinhKH;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public String getCccd() {
        return cccd;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setNgaySinhKH(Date ngaySinhKH) {
        this.ngaySinhKH = ngaySinhKH;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public KhachHang() {
    }

    public KhachHang(int idKhachHang, String tenKhachHang, Date ngaySinhKH, boolean gioiTinh, String diaChiKH, String emailKH, String cccd, String ngheNghiep, String soDienThoai) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngaySinhKH = ngaySinhKH;
        this.gioiTinh = gioiTinh;
        this.diaChiKH = diaChiKH;
        this.emailKH = emailKH;
        this.cccd = cccd;
        this.ngheNghiep = ngheNghiep;
        this.soDienThoai = soDienThoai;
    }
    
    
}
