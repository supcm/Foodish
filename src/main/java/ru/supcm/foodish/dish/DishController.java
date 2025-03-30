package ru.supcm.foodish.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/{dishId}")
    public Dish getDish(@PathVariable long dishId) {
        return dishService.getDish(dishId);
    }

    @PostMapping("/add")
    public Dish addDish(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @PatchMapping("/update/price/{dishName}")
    public Dish updatePrice(@PathVariable String dishName, @RequestParam double newPrice) {
        return dishService.updateDishPrice(dishService.getDish(dishName), newPrice);
    }
}
