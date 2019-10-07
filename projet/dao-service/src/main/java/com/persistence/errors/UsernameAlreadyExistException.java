package com.persistence.errors;


@SuppressWarnings("serial")
public class UsernameAlreadyExistException extends RuntimeException {

    private int code;

    public UsernameAlreadyExistException(int code) {
        super("Username already exist.");
        this.code = code;
    
    }

    public UsernameAlreadyExistException(String message, int code) {
        super(message);
        this.code = code;
    }

    public UsernameAlreadyExistException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public UsernameAlreadyExistException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}