/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author maluo
 */
@Entity
@Table(name = "PhieuGiaoDich")
public class PhieuGiaoDich {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PhieuGiaoDich")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ID_GiaoDich")
    private GiaoDich giaoDich;
    
    @ManyToOne
    @JoinColumn(name = "SoTaiKhoanNganHang")
    private TaiKhoanNganHang taiKhoanNganHang;
    
    @ManyToOne
    @JoinColumn(name = "ID_NhanVien")
    private NhanVien nhanVien;
    
    @Column(name = "SoTienGiaoDich")
    private long soTienGiaoDich;
    
    @Column(name = "NgayGiaoDich")
    private int ngayGiaoDich;
    
    @Column(name = "ThangGiaoDich")
    private int thangGiaoDich;
    
    @Column(name = "NamGiaoDich")
    private int namGiaoDich;
    
    @Column(name = "ThoiGianGiaoDich")
    private LocalDateTime thoiGianGiaoDich;
    
    @Column(name = "NoiDungGiaoDich", length = 100)
    private String noiDungGiaoDich;
    
    @Column(name = "TrangThaiGiaoDich")
    private int trangThaiGiaoDich;
    
    @ManyToOne
    @JoinColumn(name = "TaiKhoanNguoiNhan_Gui")
    private TaiKhoanNganHang taiKhoanNguoiNhan_Gui;

    public PhieuGiaoDich(int id, GiaoDich giaoDich, TaiKhoanNganHang taiKhoanNganHang, NhanVien nhanVien, long soTienGiaoDich, int ngayGiaoDich, int thangGiaoDich, int namGiaoDich, LocalDateTime thoiGianGiaoDich, String noiDungGiaoDich, int trangThaiGiaoDich, TaiKhoanNganHang taiKhoanNguoiNhan_Gui) {
        this.id = id;
        this.giaoDich = giaoDich;
        this.taiKhoanNganHang = taiKhoanNganHang;
        this.nhanVien = nhanVien;
        this.soTienGiaoDich = soTienGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.thangGiaoDich = thangGiaoDich;
        this.namGiaoDich = namGiaoDich;
        this.thoiGianGiaoDich = thoiGianGiaoDich;
        this.noiDungGiaoDich = noiDungGiaoDich;
        this.trangThaiGiaoDich = trangThaiGiaoDich;
        this.taiKhoanNguoiNhan_Gui = taiKhoanNguoiNhan_Gui;
    }

    public PhieuGiaoDich() {
    }

    // constructors, getters, and setters

    public int getId() {
        return id;
    }

    public GiaoDich getGiaoDich() {
        return giaoDich;
    }

    public TaiKhoanNganHang getTaiKhoanNganHang() {
        return taiKhoanNganHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public long getSoTienGiaoDich() {
        return soTienGiaoDich;
    }

    public int getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public int getThangGiaoDich() {
        return thangGiaoDich;
    }

    public int getNamGiaoDich() {
        return namGiaoDich;
    }

    public LocalDateTime getThoiGianGiaoDich() {
        return thoiGianGiaoDich;
    }

    public String getNoiDungGiaoDich() {
        return noiDungGiaoDich;
    }

    public int getTrangThaiGiaoDich() {
        return trangThaiGiaoDich;
    }

    public TaiKhoanNganHang getTaiKhoanNguoiNhan_Gui() {
        return taiKhoanNguoiNhan_Gui;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiaoDich(GiaoDich giaoDich) {
        this.giaoDich = giaoDich;
    }

    public void setTaiKhoanNganHang(TaiKhoanNganHang taiKhoanNganHang) {
        this.taiKhoanNganHang = taiKhoanNganHang;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setSoTienGiaoDich(long soTienGiaoDich) {
        this.soTienGiaoDich = soTienGiaoDich;
    }

    public void setNgayGiaoDich(int ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public void setThangGiaoDich(int thangGiaoDich) {
        this.thangGiaoDich = thangGiaoDich;
    }

    public void setNamGiaoDich(int namGiaoDich) {
        this.namGiaoDich = namGiaoDich;
    }

    public void setThoiGianGiaoDich(LocalDateTime thoiGianGiaoDich) {
        this.thoiGianGiaoDich = thoiGianGiaoDich;
    }

    public void setNoiDungGiaoDich(String noiDungGiaoDich) {
        this.noiDungGiaoDich = noiDungGiaoDich;
    }

    public void setTrangThaiGiaoDich(int trangThaiGiaoDich) {
        this.trangThaiGiaoDich = trangThaiGiaoDich;
    }

    public void setTaiKhoanNguoiNhan_Gui(TaiKhoanNganHang taiKhoanNguoiNhan_Gui) {
        this.taiKhoanNguoiNhan_Gui = taiKhoanNguoiNhan_Gui;
    }
    
}
