package com.ad.e;

public final class AdServiceTimeOutException extends RuntimeException {
    private static final String MESSAGE = "Timed out exception: Key = %s; Duration = %s";
    public AdServiceTimeOutException(String key, long duration) {
        super(String.format(MESSAGE, key, duration));
    }
}
