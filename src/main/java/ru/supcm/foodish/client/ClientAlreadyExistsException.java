package ru.supcm.foodish.client;

public class ClientAlreadyExistsException extends Exception {
    public ClientAlreadyExistsException() {
        super("Client already exists");
    }
}
