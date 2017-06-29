package com.javarush.intern.controllers;

/**
 * Created by yul on 15.06.17.
 */

import com.javarush.intern.model.User;
import com.javarush.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private int currentPage = 0;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(@RequestParam(required = false) Integer page, Model model) {
        model.addAttribute("user", new User());
        List<User> users = this.userService.getUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(5);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if (page == null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;

        model.addAttribute("page", page);
        currentPage = page;
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            model.addAttribute("listUsers", pagedListHolder.getPageList());
        } else {
            pagedListHolder.setPage(page - 1);
            model.addAttribute("listUsers", pagedListHolder.getPageList());
        }

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserbyId(id));
        List<User> users = this.userService.getUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(5);
        pagedListHolder.setPage(currentPage);
        model.addAttribute("listUsers", pagedListHolder.getPageList());

        return "users";
    }

    @RequestMapping(value = "users/search")
    public String searchUser(@RequestParam("searchName") String searchName, Model model) {
        model.addAttribute("user", new User());
        List<User> users = this.userService.getUserbyName(searchName);
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(5);
        pagedListHolder.setPage(currentPage);
        model.addAttribute("listUsers", pagedListHolder.getPageList());
        return "users";
    }
}

