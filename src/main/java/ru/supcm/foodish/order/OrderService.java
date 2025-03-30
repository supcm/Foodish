package ru.supcm.foodish.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.supcm.foodish.client.Client;
import ru.supcm.foodish.client.ClientService;
import ru.supcm.foodish.client.NoSuchClientException;
import ru.supcm.foodish.dish.Dish;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ClientService clientService;
    private final OrderRepo repository;

    public Order makeOrder(List<Dish> dishes, Client client, String commentary, Order.PaymentType type) {
        double price = dishes.stream().mapToDouble(Dish::getPrice).sum();

        Order order = Order.builder()
                .dishes(dishes)
                .client(client)
                .commentary(commentary)
                .price(price)
                .orderDate(new Date(System.currentTimeMillis()))
                .paymentType(type)
                .status(Order.Status.CREATED)
                .build();
        repository.save(order);

        return order;
    }

    public Order updateOrderStatus(Order order, Order.Status status) {
        order.setStatus(status);
        repository.save(order);
        return order;
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

    public List<Order> getOrdersInLastMonth() {
        YearMonth prevMonth = YearMonth.now().minusMonths(1);
        Date prevDate = Date.from(prevMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date lastDate = Date.from(prevMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        return repository.findByOrderDateBetween(prevDate, lastDate);
    }

    public List<Order> getOrdersInCurrentMonth() {
        YearMonth curMonth = YearMonth.now();
        Date prevDate = Date.from(curMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return repository.findByOrderDateBetween(prevDate, new Date(System.currentTimeMillis()));
    }
}
