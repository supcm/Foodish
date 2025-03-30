package ru.supcm.foodish.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.supcm.foodish.client.ClientService;
import ru.supcm.foodish.dish.Dish;
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
        List<Dish> dishes = req.dishes().stream().map(dishService::getDish).toList();
        //TODO: if client is not present, add it first and then add order
        return orderService.makeOrder(dishes, clientService.getClientByPhone(req.clientPhone()), req.commentary(), req.payment());
    }

    @PatchMapping("/update/status/{orderId}")
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

    @PatchMapping("/cancel/{orderId}")
    public Order cancelOrder(@PathVariable long orderId) {
        return orderService.updateOrderStatus(orderService.getOrderById(orderId), Order.Status.CANCELLED);
    }
}
