package ru.supcm.foodish.order;

import jakarta.persistence.*;
import lombok.*;
import ru.supcm.foodish.client.Client;
import ru.supcm.foodish.dish.Dish;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<Dish> dishes;

    @Column
    private String commentary;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        IN_PROGRESS, DONE, CANCELLED, CREATED
    }

    public enum PaymentType {
        CASH, CARD
    }
}
