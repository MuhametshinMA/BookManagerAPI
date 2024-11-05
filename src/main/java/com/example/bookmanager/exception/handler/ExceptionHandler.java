package com.example.bookmanager.exception.handler;

import com.example.bookmanager.enums.ErrorCode;

public class ExceptionHandler {
    public static <T extends Exception> T raiseException(Class<T> clazz, ErrorCode errorCode) throws T {
        try {
            T ex = clazz.getConstructor(ErrorCode.class).newInstance(errorCode);
            return ex;
        } catch (Exception e) {
            throw new RuntimeException("Raise exception fail: ", e);
        }
    }
}
