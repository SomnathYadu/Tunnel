package com.tunnel.controller;

import com.tunnel.entity.User;
import com.tunnel.entity.UserRole;
import com.tunnel.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserRolesController {

    @Autowired
    UserRoleRepository service;

    @GetMapping(path = "/r")
    public List<UserRole> retrieveAllUser() {
        return service.findByAll();
    }
}
