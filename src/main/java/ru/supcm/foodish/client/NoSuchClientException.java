package ru.supcm.foodish.client;

public class NoSuchClientException extends RuntimeException {
    public NoSuchClientException() {
        super("No such client");
    }
}
