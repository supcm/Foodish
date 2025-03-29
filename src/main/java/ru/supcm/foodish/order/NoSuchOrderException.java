package ru.supcm.foodish.order;


public class NoSuchOrderException extends Exception {
    public NoSuchOrderException() {
        super("No such order");
    }
}
