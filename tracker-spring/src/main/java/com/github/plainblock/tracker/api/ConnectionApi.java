package com.github.plainblock.tracker.api;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.plainblock.tracker.usecase.ConnectionUsecase;
import com.github.plainblock.tracker.usecase.input.ConnectionInput;
import com.github.plainblock.tracker.usecase.output.ConnectionOutput;
import com.github.plainblock.tracker.util.LogUtil;

@RestController
public class ConnectionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionApi.class);

    private final ConnectionUsecase usecase;

    @Autowired
    public ConnectionApi(ConnectionUsecase usecase) {
        this.usecase = usecase;
    }

    @GetMapping("/api/v1/connections")
    public ConnectionOutput fetchConnectionStatus(@RequestParam Map<String, String> params, HttpServletRequest request) {
        LogUtil.loggingRequest(LOGGER, request);
        ConnectionInput input = ConnectionInput.fromMap(params);
        return usecase.verifyConnection(input);
    }

}
