package com.github.plainblock.tracker.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorsController implements ErrorController {

    @RequestMapping
    public String handleErrorHtml(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && statusCode.toString().equals("404")) {
            return "error/404";
        } else {
            return "error/500";
        }
    }

}
