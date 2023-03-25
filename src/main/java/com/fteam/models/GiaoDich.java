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
@Table(name = "GiaoDich")
public class GiaoDich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_GiaoDich;

    @Column(length = 50)
    private String TenGiaoDich;

    public int getID_GiaoDich() {
        return ID_GiaoDich;
    }

    public String getTenGiaoDich() {
        return TenGiaoDich;
    }

    public void setID_GiaoDich(int ID_GiaoDich) {
        this.ID_GiaoDich = ID_GiaoDich;
    }

    public void setTenGiaoDich(String TenGiaoDich) {
        this.TenGiaoDich = TenGiaoDich;
    }

    public GiaoDich() {
    }

    public GiaoDich(int ID_GiaoDich, String TenGiaoDich) {
        this.ID_GiaoDich = ID_GiaoDich;
        this.TenGiaoDich = TenGiaoDich;
    }

}
