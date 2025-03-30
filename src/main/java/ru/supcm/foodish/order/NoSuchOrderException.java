package ru.supcm.foodish.order;


public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException() {
        super("No such order");
    }
}
