package ru.supcm.foodish.dish;

public class DishAlreadyExistsException extends RuntimeException {
    public DishAlreadyExistsException() {
        super("Dish already exists");
    }
}
