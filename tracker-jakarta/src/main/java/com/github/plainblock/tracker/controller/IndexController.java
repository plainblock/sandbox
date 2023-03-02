package com.github.plainblock.tracker.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
public class IndexController {

    @GET
    @Path("/public/web/top")
    @Produces(MediaType.TEXT_HTML)
    public String renderIndex() {
        return "index";
    }

}
