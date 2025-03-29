package ru.supcm.foodish.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo repository;

    public Dish getDish(String name) throws NoSuchDishException {
        return repository.findByName(name).orElseThrow(NoSuchDishException::new);
    }

    public Dish getDish(long id) throws NoSuchDishException {
        return repository.findById(id).orElseThrow(NoSuchDishException::new);
    }

    public Dish addDish(Dish dish) {
        //TODO: add check on already existing name
        return repository.save(dish);
    }

    public Dish updateDishPrice(Dish dish, double price) {
        dish.setPrice(price);
        return repository.save(dish);
    }
}
