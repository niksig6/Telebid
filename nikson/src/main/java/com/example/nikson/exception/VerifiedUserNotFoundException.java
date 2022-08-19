package com.example.nikson.exception;

public class VerifiedUserNotFoundException extends Exception {
    private static final String MESSAGE = "Incorrect login credentials for verified user, please try again!";

    public VerifiedUserNotFoundException() {
        super(MESSAGE);
    }
}
