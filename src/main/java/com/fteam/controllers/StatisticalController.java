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
public class StatisticalController {

    private String messageStatistical = null;
    private String messageSuccessStatistical = null;

    @RequestMapping(value = "statistical")
    public String statistical(Model model) {
        model.addAttribute("pageTitle", "Thống kê");
        if (messageStatistical != null || messageSuccessStatistical != null) {
            model.addAttribute("messageStatistical", messageStatistical);
            model.addAttribute("messageSuccessStatistical", messageSuccessStatistical);
            messageStatistical = null;
            messageSuccessStatistical = null;
        }
        return "home/statistical";
    }
}
