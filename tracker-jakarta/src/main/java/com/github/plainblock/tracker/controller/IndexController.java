package com.github.plainblock.tracker.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/public/web/top")
public class IndexController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response renderIndex() {
        return Response.ok("Hello, World!").build();
    }

}
