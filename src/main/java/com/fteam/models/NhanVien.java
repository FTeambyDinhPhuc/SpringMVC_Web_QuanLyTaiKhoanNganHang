/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tuans
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @Column(name = "TenDangNhap")
    private String TenDangNhap;

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String NamSinh) {
        this.NamSinh = NamSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getID_ChưcVu() {
        return ID_ChưcVu;
    }

    public void setID_ChưcVu(String ID_ChưcVu) {
        this.ID_ChưcVu = ID_ChưcVu;
    }

    public NhanVien(String TenDangNhap, String MatKhau, String TenNhanVien, String NamSinh, String GioiTinh, String DiaChi, String Email, String SoDienThoai, String TrangThai, String ID_ChưcVu) {
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.TenNhanVien = TenNhanVien;
        this.NamSinh = NamSinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SoDienThoai = SoDienThoai;
        this.TrangThai = TrangThai;
        this.ID_ChưcVu = ID_ChưcVu;
    }

    @Column(name = "MatKhau")
    private String MatKhau;

    @Column(name = "TenNhanVien")
    private String TenNhanVien;

    @Column(name = "NamSinh")
    private String NamSinh;

    @Column(name = "GioiTinh")
    private String GioiTinh;

    @Column(name = "DiaChi")
    private String DiaChi;
    
    @Column(name = "Email")
    private String Email;

    @Column(name = "SoDienThoai")
    private String SoDienThoai;

    @Column(name = "TrangThai")
    private String TrangThai;

    @Column(name = "ID_ChưcVu")
    private String ID_ChưcVu;
    // constructors, getters and setters, and other methods

    

    public NhanVien() {
    }
}
