package com.github.plainblock.tracker.usecase.output;

import com.github.plainblock.tracker.domain.model.ConnectionStatus;

public record ConnectionOutput(String ip, String fqdn, boolean reachable) {

    public static ConnectionOutput fromEntity(ConnectionStatus status) {
        return new ConnectionOutput(status.hostAddress(), status.hostName(), status.reachable());
    }

}
