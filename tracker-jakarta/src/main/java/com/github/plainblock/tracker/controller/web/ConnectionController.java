package com.github.plainblock.tracker.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.plainblock.tracker.controller.constant.WebEndpoint;
import com.github.plainblock.tracker.util.LogUtil;

@Path(WebEndpoint.CONNECTION)
public class ConnectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionController.class);

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String renderConnectionStatus() {
        LogUtil.loggingRequest(LOGGER, request);
        return "status/connection";
    }

}
