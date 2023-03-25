/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author maluo
 */
@Entity
@Table(name = "The")
public class The {

    @Id
    @Column(name = "SoThe")
    private Long soThe;
    
    @Column(name = "NgayCapThe")
    private Date ngayCapThe;
    
    @Column(name = "NgayHetHan")
    private Date ngayHetHan;
    
    @Column(name = "CVV")
    private Integer cvv;
    
    @Column(name = "TrangThaiThe")
    private Integer trangThaiThe;
    
    @Column(name = "SoTaiKhoanNganHang")
    private Long soTaiKhoanNganHang;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LoaiThe")
    private LoaiThe loaiThe;

    // Constructor không tham số
    public The() {
    }

    // Constructor có tham số
    public The(Long soThe, Date ngayCapThe, Date ngayHetHan, Integer cvv, Integer trangThaiThe, Long soTaiKhoanNganHang, LoaiThe loaiThe) {
        this.soThe = soThe;
        this.ngayCapThe = ngayCapThe;
        this.ngayHetHan = ngayHetHan;
        this.cvv = cvv;
        this.trangThaiThe = trangThaiThe;
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.loaiThe = loaiThe;
    }

    // Getter và Setter
    public Long getSoThe() {
        return soThe;
    }

    public void setSoThe(Long soThe) {
        this.soThe = soThe;
    }

    public Date getNgayCapThe() {
        return ngayCapThe;
    }

    public void setNgayCapThe(Date ngayCapThe) {
        this.ngayCapThe = ngayCapThe;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getTrangThaiThe() {
        return trangThaiThe;
    }

    public void setTrangThaiThe(Integer trangThaiThe) {
        this.trangThaiThe = trangThaiThe;
    }

    public Long getSoTaiKhoanNganHang() {
        return soTaiKhoanNganHang;
    }

    public void setSoTaiKhoanNganHang(Long soTaiKhoanNganHang) {
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
    }

    public LoaiThe getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(LoaiThe loaiThe) {
        this.loaiThe = loaiThe;
    }
}
