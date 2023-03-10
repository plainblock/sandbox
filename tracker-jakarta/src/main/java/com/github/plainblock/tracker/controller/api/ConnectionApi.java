package com.github.plainblock.tracker.controller.api;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.plainblock.tracker.controller.constant.ApiEndpoint;
import com.github.plainblock.tracker.usecase.ConnectionUsecase;
import com.github.plainblock.tracker.usecase.input.ConnectionInput;
import com.github.plainblock.tracker.usecase.output.ConnectionOutput;
import com.github.plainblock.tracker.util.LogUtil;

@Path(ApiEndpoint.CONNECTION)
@Produces(MediaType.APPLICATION_JSON)
public class ConnectionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionApi.class);

    @Inject
    private ConnectionUsecase usecase;

    @Context
    private HttpServletRequest request;

    @GET
    public Response fetchConnectionStatus(@QueryParam("host") String host) {
        LogUtil.loggingRequest(LOGGER, request);
        ConnectionInput input = new ConnectionInput(host);
        ConnectionOutput output = usecase.verifyConnection(input);
        return Response.ok(output).build();
    }

}
