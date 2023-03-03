package com.github.plainblock.tracker.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class ConnectionController {

    @GET
    @Path("/public/status/connection")
    @Produces(MediaType.TEXT_HTML)
    public String renderConnectionStatus() {
        return "status/connection";
    }

}
