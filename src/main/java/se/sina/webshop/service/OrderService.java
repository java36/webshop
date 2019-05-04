package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.CustomerRepository;
import se.sina.webshop.repository.ItemRepository;
import se.sina.webshop.repository.OrderItemRepository;
import se.sina.webshop.repository.OrderRepository;

@Service
public final class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ItemRepository itemRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }
}
