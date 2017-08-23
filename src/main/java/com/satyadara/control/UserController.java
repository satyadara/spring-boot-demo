package com.satyadara.control;

import com.satyadara.model.User;
import com.satyadara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllUser(Model model)  {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postAUser(Model model, User user) {
        User user1 = userService.postAUser(user);
        model.addAttribute("user", user1);
        return "redirect:/";
    }

}
