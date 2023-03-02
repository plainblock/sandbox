package com.github.plainblock.tracker.infra.ping;

import java.io.IOException;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.github.plainblock.tracker.domain.model.ConnectionStatus;
import com.github.plainblock.tracker.domain.repository.ConnectionRepository;

@Repository
@Profile("default")
public class PingRepository implements ConnectionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingRepository.class);
    private static final String LOOPBACK = "127.0.0.1";
    private static final int TIMEOUT = 5000;

    @Override
    public ConnectionStatus fetchStatus() {
        return fetchStatus(LOOPBACK, TIMEOUT);
    }

    @Override
    public ConnectionStatus fetchStatus(String host) {
        return fetchStatus(host, TIMEOUT);
    }

    @Override
    public ConnectionStatus fetchStatus(String host, int timeout) {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            String address = inetAddress.getHostAddress();
            String name = inetAddress.getHostName();
            boolean reachable = inetAddress.isReachable(timeout);
            return new ConnectionStatus(address, name, reachable);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return ConnectionStatus.empty();
        }
    }

}
