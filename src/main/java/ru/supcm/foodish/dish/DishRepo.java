package ru.supcm.foodish.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {
    Optional<Dish> findByName(String name);
}
