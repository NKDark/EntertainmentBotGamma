package com.nkdark.web.controller;

import com.nkdark.web.entity.Model;
import com.nkdark.web.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotController {

    @Autowired
    BotService service;

    @PostMapping("/bot/info")
    public List<Model> getBotInfo() {
        return service.getBotInfo();
    }

}
