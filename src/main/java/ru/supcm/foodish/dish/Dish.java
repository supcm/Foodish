package ru.supcm.foodish.dish;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dishes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column()
    private String description;
    @Column(nullable = false)
    private double price;
}
