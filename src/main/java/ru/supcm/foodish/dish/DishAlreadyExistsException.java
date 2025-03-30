package ru.supcm.foodish.dish;

public class DishAlreadyExistsException extends Exception {
    public DishAlreadyExistsException() {
        super("Dish already exists");
    }
}
