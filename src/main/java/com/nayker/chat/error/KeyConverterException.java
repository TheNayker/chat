package com.nayker.chat.error;

public class KeyConverterException extends RuntimeException {

    public KeyConverterException() {
    }

    public KeyConverterException(String message) {
        super(message);
    }

    public KeyConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyConverterException(Throwable cause) {
        super(cause);
    }

}
