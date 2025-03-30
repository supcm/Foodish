package ru.supcm.foodish.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.supcm.foodish.client.Client;
import ru.supcm.foodish.client.ClientService;
import ru.supcm.foodish.client.NoSuchClientException;
import ru.supcm.foodish.dish.Dish;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ClientService clientService;
    private final OrderRepo repository;

    public double makeOrder(List<Dish> dishes, Client client, String commentary, Order.PaymentType type) {
        Order order = Order.builder()
                .dishes(dishes)
                .client(client)
                .commentary(commentary)
                .paymentType(type)
                .status(Order.Status.CREATED)
                .build();
        repository.save(order);

        return order.getDishes().stream().mapToDouble(Dish::getPrice).sum();
    }

    public void updateOrderStatus(Order order, Order.Status status) {
        order.setStatus(status);
        repository.save(order);
    }

    public Order getOrderById(long id) throws NoSuchOrderException {
        return repository.findById(id).orElseThrow(NoSuchOrderException::new);
    }

    public List<Order> getClientOrders(Client client) {
        return repository.findByClient(client);
    }

    public List<Order> getClientOrdersByPhone(String phoneNumber) throws NoSuchClientException {
        Client client = clientService.getClientByPhone(phoneNumber);
        return getClientOrders(client);
    }

    public List<Order> getOrdersInStatus(Order.Status status) {
        return repository.findByStatus(status);
    }
}
