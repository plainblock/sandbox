package com.github.plainblock.tracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.plainblock.tracker.constant.WebEndpoint;
import com.github.plainblock.tracker.util.LogUtil;

@Path(WebEndpoint.TOP)
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response renderIndex() {
        LogUtil.loggingRequest(LOGGER, request);
        return Response.ok("Hello, World!").build();
    }

}
