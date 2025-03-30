package ru.supcm.foodish.dish;

public class NoSuchDishException extends RuntimeException {
    public NoSuchDishException() {
        super("No such dish");
    }
}
