package com.github.plainblock.tracker.domain.repository;

import com.github.plainblock.tracker.domain.model.ConnectionStatus;

public interface ConnectionRepository {

    ConnectionStatus fetchStatus();

    ConnectionStatus fetchStatus(String host);

    ConnectionStatus fetchStatus(String host, int timeout);

}
