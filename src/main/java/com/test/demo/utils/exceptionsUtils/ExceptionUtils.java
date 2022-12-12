package com.test.demo.utils.exceptionsUtils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class ExceptionUtils {
    public static String subst(String string, Object... objects) {
        return MessageFormatter.arrayFormat(string, objects).getMessage();
    }
}
