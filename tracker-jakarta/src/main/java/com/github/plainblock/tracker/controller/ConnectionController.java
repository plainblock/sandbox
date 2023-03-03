package com.github.plainblock.tracker.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.github.plainblock.tracker.constant.WebEndpoint;

@Path(WebEndpoint.CONNECTION)
public class ConnectionController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String renderConnectionStatus() {
        return "status/connection";
    }

}
