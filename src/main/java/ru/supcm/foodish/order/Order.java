package ru.supcm.foodish.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.supcm.foodish.client.Client;
import ru.supcm.foodish.dish.Dish;

import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@Data
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<Dish> dishes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        IN_PROGRESS, DONE, CANCELLED, CREATED
    }
}
