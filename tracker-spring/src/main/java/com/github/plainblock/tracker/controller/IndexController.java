package com.github.plainblock.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/public/web/top")
    public String renderIndex() {
        return "index";
    }

}
