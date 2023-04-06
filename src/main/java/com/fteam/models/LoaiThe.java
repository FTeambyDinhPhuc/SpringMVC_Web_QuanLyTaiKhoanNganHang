/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.models;

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
@Table(name = "LoaiThe")
public class LoaiThe {

    public static Object fromValue(int loaithe) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LoaiThe")
    private int id;

    @Column(name = "TenLoaiThe")
    private String tenLoaiThe;

    @Column(name = "PhiPhatHanh")
    private int phiPhatHanh;

    @Column(name = "PhiDuyTri")
    private int phiDuyTri;

    @Column(name = "PhiThuongNien")
    private int phiThuongNien;

    // constructors, getters, setters

    public int getId() {
        return id;
    }

    public String getTenLoaiThe() {
        return tenLoaiThe;
    }

    public int getPhiPhatHanh() {
        return phiPhatHanh;
    }

    public int getPhiDuyTri() {
        return phiDuyTri;
    }

    public int getPhiThuongNien() {
        return phiThuongNien;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenLoaiThe(String tenLoaiThe) {
        this.tenLoaiThe = tenLoaiThe;
    }

    public void setPhiPhatHanh(int phiPhatHanh) {
        this.phiPhatHanh = phiPhatHanh;
    }

    public void setPhiDuyTri(int phiDuyTri) {
        this.phiDuyTri = phiDuyTri;
    }

    public void setPhiThuongNien(int phiThuongNien) {
        this.phiThuongNien = phiThuongNien;
    }

    public LoaiThe() {
    }

    public LoaiThe(int id, String tenLoaiThe, int phiPhatHanh, int phiDuyTri, int phiThuongNien) {
        this.id = id;
        this.tenLoaiThe = tenLoaiThe;
        this.phiPhatHanh = phiPhatHanh;
        this.phiDuyTri = phiDuyTri;
        this.phiThuongNien = phiThuongNien;
    }
    
}
