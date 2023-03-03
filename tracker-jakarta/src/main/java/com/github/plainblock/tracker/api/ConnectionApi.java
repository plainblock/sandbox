package com.github.plainblock.tracker.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import com.github.plainblock.tracker.constant.ApiEndpoint;
import com.github.plainblock.tracker.usecase.ConnectionUsecase;
import com.github.plainblock.tracker.usecase.input.ConnectionInput;
import com.github.plainblock.tracker.usecase.output.ConnectionOutput;
import com.github.plainblock.tracker.util.*;

@Path(ApiEndpoint.CONNECTION)
@Produces(MediaType.APPLICATION_JSON)
public class ConnectionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionApi.class);

    @Inject
    private ConnectionUsecase usecase;

    @GET
    public Response fetchConnectionStatus(@QueryParam("host") String host) {
        LogUtil.loggingRequest(LOGGER, HttpMethod.GET, ApiEndpoint.CONNECTION, Map.of("host", TextUtil.requireNonNull(host)));
        ConnectionInput input = new ConnectionInput(host);
        ConnectionOutput output = usecase.verifyConnection(input);
        return Response.ok(output).build();
    }

}
