package ru.supcm.foodish.dish;

public class NoSuchDishException extends Exception {
    public NoSuchDishException() {
        super("No such dish");
    }
}
