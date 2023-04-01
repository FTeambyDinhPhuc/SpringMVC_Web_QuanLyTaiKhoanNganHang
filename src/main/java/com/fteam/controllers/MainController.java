/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dinhp
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/", "/home"})
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("Ten") == null) {
            return "redirect:auth/login";
        } else {
            model.addAttribute("pageTitle", "Trang chủ");
            return "home/index";
        }
    }
}
