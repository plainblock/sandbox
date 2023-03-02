package com.github.plainblock.tracker.domain.model;

import com.github.plainblock.tracker.util.TextUtil;

public record ConnectionStatus(String hostAddress, String hostName, boolean reachable) {

    public ConnectionStatus {
        if (!TextUtil.hasText(hostAddress) && !TextUtil.hasText(hostName) && reachable) {
            throw new RuntimeException("hostAddress or hostName is required");
        }
    }

    public static ConnectionStatus empty() {
        return new ConnectionStatus("", "", false);
    }

}
