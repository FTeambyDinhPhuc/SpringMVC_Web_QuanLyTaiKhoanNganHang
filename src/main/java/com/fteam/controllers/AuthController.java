/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dinhp
 */
@Controller
public class AuthController {
    @RequestMapping(value = "/login")
    public String Login(Model model) {
        return "login";
    }
    
    @RequestMapping(value = "/register")
    public String Register(Model model) {
        return "register";
    }
}