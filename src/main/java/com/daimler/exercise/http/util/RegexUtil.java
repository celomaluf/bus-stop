package com.daimler.exercise.http.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private RegexUtil() {}

    /**
     * Matches $.string:
     */
    public static final Pattern VALIDATION_ERROR_PATTERN = Pattern.compile("((\\$(\\[[0-9]\\])?\\.\\w+: ))");


}
