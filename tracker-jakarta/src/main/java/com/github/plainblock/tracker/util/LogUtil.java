package com.github.plainblock.tracker.util;

import org.slf4j.Logger;

import java.util.Map;

public class LogUtil {

    public static void loggingRequest(Logger logger, String method, String endpoint) {
        loggingRequest(logger, method, endpoint, null);
    }

    public static void loggingRequest(Logger logger, String method, String endpoint, Map<String, String> query) {
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append(" ");
        sb.append(endpoint);
        if (query != null && !query.isEmpty()) {
            sb.append("?");
            query.forEach((k, v)->{
                if (v != null && !v.isBlank()) {
                    sb.append(String.format("%s=%s,", k, v));
                }
            });
            sb.deleteCharAt(sb.length() - 1);
        }
        logger.info(sb.toString());
    }

}
