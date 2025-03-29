package ru.supcm.foodish.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.supcm.foodish.client.Client;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByClient(Client client);
    List<Order> findByStatus(Order.Status status);
}
