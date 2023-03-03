package com.github.plainblock.tracker.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class IndexController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Response renderTop() {
        return Response.ok("Hello, World!").build();
    }

    @GET
    @Path("/public/web/top")
    @Produces(MediaType.TEXT_HTML)
    public Response renderIndex() {
        return Response.ok("Hello, World!").build();
    }

}
