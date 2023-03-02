package com.github.plainblock.tracker.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;

@RequestScoped
//@Path("${server.error.path:${error.path:/error}}")
public class ErrorsController {

    public String renderError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && statusCode.toString().equals("404")) {
            return "error/404";
        } else {
            return "error/500";
        }
    }

}
