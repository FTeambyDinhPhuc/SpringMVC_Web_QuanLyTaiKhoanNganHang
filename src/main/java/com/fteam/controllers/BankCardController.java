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
@RequestMapping("/home/")
public class BankCardController {

    @RequestMapping(value = "bank_card_management")
    public String BankCards(Model model) {
        model.addAttribute("pageTitle", "Dịch vụ thẻ");
        return "home/bank_card_management";
    }
}
