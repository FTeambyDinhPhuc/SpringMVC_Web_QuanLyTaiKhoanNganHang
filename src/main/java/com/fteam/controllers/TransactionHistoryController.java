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
public class TransactionHistoryController {

    private String messageTransactionHistory = null;
    private String messageSuccessTransactionHistory = null;

    @RequestMapping(value = "transaction_history")
    public String TransactionHistory(Model model) {
        model.addAttribute("pageTitle", "Thống kê giao dịch khách hàng");
        if (messageTransactionHistory != null || messageSuccessTransactionHistory != null) {
            model.addAttribute("messageTransactionHistory", messageTransactionHistory);
            model.addAttribute("messageSuccessTransactionHistory", messageSuccessTransactionHistory);
            messageTransactionHistory = null;
            messageSuccessTransactionHistory = null;
        }
        return "home/transaction_history";
    }
}
