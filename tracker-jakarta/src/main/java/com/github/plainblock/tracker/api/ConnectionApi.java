package com.github.plainblock.tracker.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.github.plainblock.tracker.usecase.ConnectionUsecase;
import com.github.plainblock.tracker.usecase.input.ConnectionInput;
import com.github.plainblock.tracker.usecase.output.ConnectionOutput;

@Path("/api/v1/connections")
@Produces(MediaType.APPLICATION_JSON)
public class ConnectionApi {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionApi.class);

    @Inject
    private ConnectionUsecase usecase;

    @GET
    public Response fetchConnectionStatus(@QueryParam("host") String host) {
        ConnectionInput input = new ConnectionInput(host);
        ConnectionOutput output = usecase.verifyConnection(input);
        return Response.ok(output).build();
    }

}
