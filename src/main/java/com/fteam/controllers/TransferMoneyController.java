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
public class TransferMoneyController {

    private String messageTransferMoney = null;
    private String messageSuccessTransferMoney = null;

    @RequestMapping(value = "transfer_money")
    public String TransferMoney(Model model) {
        model.addAttribute("pageTitle", "Chuyển khoản");
        if (messageTransferMoney != null || messageSuccessTransferMoney != null) {
            model.addAttribute("messageTransferMoney", messageTransferMoney);
            model.addAttribute("messageSuccessTransferMoney", messageSuccessTransferMoney);
            messageTransferMoney = null;
            messageSuccessTransferMoney = null;
        }
        return "home/transfer_money";
    }
}
