/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fteam.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dinhp
 */
@Controller
public class CustomerController {

    @RequestMapping(value = "/customer_management")
    public String Customers(Model model) {
        return "customer_management";
    }

    @RequestMapping(value = "/customer_detail")
    public String CustomerDetail(Model model) {
        return "customer_detail";
    }


}
