package com.github.plainblock.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnectionController {

    @GetMapping("/public/status/connection")
    public String renderConnectionStatus() {
        return "status/connection";
    }

}
