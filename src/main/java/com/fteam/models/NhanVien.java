/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tuans
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NhanVien")
    private Integer id;

    @Column(name = "TenDangNhap", length = 50)
    private String tenDangNhap;

    @Column(name = "MatKhau", length = 50)
    private String matKhau;

    @Column(name = "TenNhanVien", length = 100)
    private String tenNhanVien;

    @Column(name = "NamSinh")
    private Date namSinh;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @Column(name = "DiaChi", length = 100)
    private String diaChi;

    @Column(name = "CCCD", length = 15)
    private String cccd;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "SoDienThoai", length = 11)
    private String soDienThoai;

    @Column(name = "TrangThaiTaiKhoan")
    private Integer trangThaiTaiKhoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ChucVu")
    private ChucVu chucVu;

    // Constructors, getters, and setters

    public Integer getId() {
        return id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getCccd() {
        return cccd;
    }

    public String getEmail() {
        return email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public Integer getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setTrangThaiTaiKhoan(Integer trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public NhanVien() {
    }

    public NhanVien(Integer id, String tenDangNhap, String matKhau, String tenNhanVien, Date namSinh, Boolean gioiTinh, String diaChi, String cccd, String email, String soDienThoai, Integer trangThaiTaiKhoan, ChucVu chucVu) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.tenNhanVien = tenNhanVien;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.cccd = cccd;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
        this.chucVu = chucVu;
    }
    
}
