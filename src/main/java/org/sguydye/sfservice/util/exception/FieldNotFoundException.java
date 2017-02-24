package org.sguydye.sfservice.util.exception;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException() {
        super();
    }

    public FieldNotFoundException(String message) {
        super(message);
    }
}
