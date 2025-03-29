package ru.supcm.foodish.client;

public class NoSuchClientException extends Exception {
    public NoSuchClientException() {
        super("No such client");
    }
}
