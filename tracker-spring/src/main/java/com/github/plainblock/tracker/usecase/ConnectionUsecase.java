package com.github.plainblock.tracker.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.plainblock.tracker.domain.model.ConnectionStatus;
import com.github.plainblock.tracker.domain.repository.ConnectionRepository;
import com.github.plainblock.tracker.usecase.input.ConnectionInput;
import com.github.plainblock.tracker.usecase.output.ConnectionOutput;

@Service
public class ConnectionUsecase {

    private final ConnectionRepository repository;

    @Autowired
    public ConnectionUsecase(ConnectionRepository repository) {
        this.repository = repository;
    }

    public ConnectionOutput verifyConnection(ConnectionInput input) {
        ConnectionStatus status = repository.fetchStatus(input.host());
        return ConnectionOutput.fromEntity(status);
    }

}
