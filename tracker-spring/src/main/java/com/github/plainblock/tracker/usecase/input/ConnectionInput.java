package com.github.plainblock.tracker.usecase.input;

import java.util.Map;

public record ConnectionInput(String host) {

    public static ConnectionInput fromMap(Map<String, String> map) {
        String host = map.getOrDefault("host", "");
        return new ConnectionInput(host);
    }

}
