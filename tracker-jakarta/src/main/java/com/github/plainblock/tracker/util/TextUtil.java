package com.github.plainblock.tracker.util;

import java.util.Objects;

public class TextUtil {

    public static boolean hasText(String target) {
        return target != null && !target.isBlank();
    }

    public static String requireNonNull(String target) {
        return Objects.requireNonNullElse(target, "");
    }

}
