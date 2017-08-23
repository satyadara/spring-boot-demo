package com.satyadara.springdemo.controller;

import com.satyadara.springdemo.model.User;
import com.satyadara.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {


    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)    //INDEX & List User
    public String getAllUser(Model model)  {
        List<User> userList = userService.getUserList();
        model.addAttribute("messages", "Tampil Semua Pengguna.");
        model.addAttribute("users", userList);
        return "index";
    }

    @GetMapping(value = "/create")      //Masuk ke halaman buat
    public String gotoCreate(Model model)   {
        model.addAttribute("messages", "Membuat Pengguna.");
        model.addAttribute("user", new User());
        return "create";
    }


    @GetMapping(value = "/user/{id}")      //Masuk ke halaman sunting
    public String gotoUpdate(Model model, @PathVariable Long id)   {
        User user = userService.getAUser(id);
        model.addAttribute("messages", "Sunting Pengguna.");
        model.addAttribute("user", user);
        return "create";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)   //Melakukan post User
    public String postAUser(Model model, User x) {
        User user1 = userService.postAUser(x);
        model.addAttribute("messages", "Menyimpan Pengguna.");
        model.addAttribute("user", user1);
        return "redirect:/";
    }


}
