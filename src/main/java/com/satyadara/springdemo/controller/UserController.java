package com.satyadara.springdemo.controller;

import com.satyadara.springdemo.model.User;
import com.satyadara.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //Menandakan kelas ini adalah Controller (Berbeda dengan RestController)
public class UserController {


    UserService userService;    //Menggunakan service, karena repository di implementasikan di UserService

    @Autowired
    public UserController(UserService userService) {    //Cara menggunakan Autowired yang aman
        this.userService = userService;                 //dan tanpa warning
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)    //INDEX & List User
    public String getAllUser(Model model)  {
        List<User> userList = userService.getUserList();
        model.addAttribute("messages", "Tampil Semua Pengguna.");
        model.addAttribute("users", userList);
        return "index"; //index.html
    }

    @GetMapping(value = "/create")      //Masuk ke halaman buat
    public String gotoCreate(Model model)   {
        model.addAttribute("messages", "Membuat Pengguna.");
        model.addAttribute("user", new User());
        return "create"; //create.html
    }


    @GetMapping(value = "/user/{id}")      //Masuk ke halaman sunting
    public String gotoUpdate(Model model, @PathVariable Long id)   {
        User user = userService.getAUser(id);
        model.addAttribute("messages", "Sunting Pengguna.");
        model.addAttribute("user", user);
        return "create"; //create.html
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)   //Melakukan post
    public String postAUser(Model model, User x) {
        User user1 = userService.postAUser(x);
        model.addAttribute("messages", "Menyimpan Pengguna.");
        model.addAttribute("user", user1);
        return "redirect:/"; //redirect ke Mapping "/"
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET) //Melakukan hapus pengguna, masih sedikit
    public String deleteAUser(Model model, @PathVariable Long id)   {   //aneh karena menggunakan method GET.
        userService.deleteAUser(id);
        model.addAttribute("messages", "Hapus Pengguna.");
        return "redirect:/"; //redirect ke Mapping "/"
    }
}
