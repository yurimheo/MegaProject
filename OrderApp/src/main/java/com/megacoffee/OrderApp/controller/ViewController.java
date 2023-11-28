package com.megacoffee.OrderApp.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {


    //메뉴검색
    @GetMapping("/menu_search")
    public String menuSearch() {
        return "/userApp/menu_search";
    }

    //주문선택
    @GetMapping("/order_choice")
    public String orderChoice() {
        return "/userApp/order_choice";
    }

    //매장선택
    @GetMapping("/store_choice")
    public String storeChoice() {
        return "/userApp/store_choice";
    }

    //매장검색
    @GetMapping("/store_search")
    public String storeSearch() {
        return "/userApp/store_search";
    }
}