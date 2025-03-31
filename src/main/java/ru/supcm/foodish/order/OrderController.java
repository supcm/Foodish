package ru.supcm.foodish.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.supcm.foodish.client.ClientService;
import ru.supcm.foodish.dish.DishService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final DishService dishService;

    @PostMapping("/add")
    public double addOrder(@RequestBody AddOrderRequest req) {
        return orderService
                .makeOrder(
                        req.dishes().stream().map(dishService::getDish).toList(),
                        clientService.getClientByPhone(req.clientPhone()),
                        req.commentary(),
                        req.payment()
                )
                .getPrice();
    }

    @PatchMapping("/{orderId}/status/")
    public Order updateOrderStatus(@PathVariable long orderId) {
        Order order = orderService.getOrderById(orderId);
        Order.Status status = order.getStatus();
        switch (status) {
            case CREATED -> status = Order.Status.IN_PROGRESS;
            case IN_PROGRESS -> status = Order.Status.DONE;
            default -> status = Order.Status.CANCELLED;
        }
        return orderService.updateOrderStatus(order, status);
    }

    @PatchMapping("/{orderId}/cancel/")
    public Order cancelOrder(@PathVariable long orderId) {
        return orderService.updateOrderStatus(orderService.getOrderById(orderId), Order.Status.CANCELLED);
    }

    @GetMapping("/")
    public Order getOrder(@RequestParam long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/{clientPhone}/orders/")
    public List<Order> getOrders(@PathVariable String clientPhone) {
        return orderService.getClientOrders(clientService.getClientByPhone(clientPhone));
    }

    @GetMapping("/status")
    public List<Order> getOrders(@RequestParam Order.Status status) {
        return orderService.getOrdersInStatus(status);
    }
}
