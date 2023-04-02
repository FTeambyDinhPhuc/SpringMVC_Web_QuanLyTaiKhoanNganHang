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
public class TransactionMoneyController {

    private String messageTransactionMoney = null;
    private String messageSuccessTransactionMoney = null;

    @RequestMapping(value = "transaction_money")
    public String TransactionMoney(Model model) {
        model.addAttribute("pageTitle", "Nạp tiền - Rút tiền");
        if (messageTransactionMoney != null || messageSuccessTransactionMoney != null) {
            model.addAttribute("messageTransactionMoney", messageTransactionMoney);
            model.addAttribute("messageSuccessTransactionMoney", messageSuccessTransactionMoney);
            messageTransactionMoney = null;
            messageSuccessTransactionMoney = null;
        }
        return "home/transaction_money";
    }
}
