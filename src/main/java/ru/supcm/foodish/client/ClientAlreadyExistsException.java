package ru.supcm.foodish.client;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException() {
        super("Client already exists");
    }
}
