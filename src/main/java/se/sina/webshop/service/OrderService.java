package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.*;
import se.sina.webshop.repository.CustomerRepository;
import se.sina.webshop.repository.ItemRepository;
import se.sina.webshop.repository.OrderItemRepository;
import se.sina.webshop.repository.OrderRepository;
import se.sina.webshop.service.exception.CustomerExceptions.CustomerNumberNotFound;
import se.sina.webshop.service.exception.FormatExceptions.ActiveNotValid;
import se.sina.webshop.service.exception.OrderExceptions.OrderItemNumberNotFound;
import se.sina.webshop.service.exception.OrderExceptions.OrderNumberNotFound;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ItemRepository itemRepository, ItemService itemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    public Order createOrder(Order order){
        Customer customer = customerService.check(order.getCustomer().getEmail());
        order.setOrderNumber(UUID.randomUUID());
        order.setActive(true);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setTotal(0.0);
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    public OrderItem createOrderItem(UUID orderNumber, UUID itemNumber){
        Item item = itemService.checkItemStatus(itemNumber);
        Order order = checkOrder(orderNumber);
        OrderItem orderItem = new OrderItem(UUID.randomUUID(), item, order);
        orderItem.setShipped(false);
        OrderItem saved = orderItemRepository.save(orderItem);
        item.setItemStatus(ItemStatus.SOLD);
        itemService.update(item.getItemNumber(), item, item.getModel());
        itemService.checkModelStatus(item);
        order.addToTotal(item.getModel().getPrice());
        updateOrder(order.getOrderNumber(), order);
        return saved;
    }

    public Order findOrderByNumber(UUID orderNumber){
        return checkOrder(orderNumber);
    }
    public OrderItem findOrderItemByNumber(UUID orderItemNumber){
        return checkOrderItem(orderItemNumber);
    }



    public List<Order> findOrders(String customerEmail, String active){
        active = format(active);
        customerEmail = format(customerEmail);

        if(!isBlank(customerEmail)){
            if(!isBlank(active)){
                return orderRepository.findAllByCustomerEmailAndActive(customerEmail, Boolean.valueOf(active));
            }
            return orderRepository.findAllByCustomerEmail(customerEmail);
        }
        else if(!isBlank(active)){
            return orderRepository.findAllByActive(Boolean.valueOf(active));
        }
        return orderRepository.findAll();
    }

    public List<OrderItem> findOrderItems(UUID orderNumber, String customerEmail, String active){
        active = format(active);
        customerEmail = format(customerEmail);
        List<OrderItem> orderItems = new ArrayList<>();

        if(!isBlank(customerEmail)){
            List<Order> orders = orderRepository.findAllByCustomerEmail(customerEmail);
            if(!isBlank(active)){
                Boolean shipped = !Boolean.valueOf(active);
                for(Order o : orders){
                    orderItems.addAll(orderItemRepository.findAllByOrderOrderNumberAndShipped(o.getOrderNumber(), shipped));
                }
            }
            for(Order o : orders){
                orderItems.addAll(orderItemRepository.findAllByOrderOrderNumber(o.getOrderNumber()));
            }
            return orderItems;
        }
        else if(orderNumber != null){
            return orderItemRepository.findAllByOrderOrderNumber(orderNumber);
        }
        else if(!isBlank(active)){
            return orderItemRepository.findAllByShipped(!Boolean.valueOf(active));
        }
            return orderItemRepository.findAll();
    }

//    public List<OrderItem> findCustomerOrderItems(String customerEmail, String active){
//        active = format(active);
//        Customer customer = customerService.check(customerEmail);
//        List<Order> orders;
//        List<OrderItem> orderItems = new ArrayList<>();
//        if(active.equals("true")){
//            orders = findOrders(customerEmail, "true");
//            for(Order o : orders){
//                List<OrderItem> resultItems = orderItemRepository.findAllByOrder(o);
//                for(OrderItem item : orderItems){
//                    if(!item.isShipped()){
//                        orderItems.add(item);
//                    }
//                }
//            }
//            return orderItems;
//        }
//        else if(active.equals("false")){
//            orders = findOrders(customerEmail, "false");
//            for(Order o : orders){
//                orderItems.addAll(orderItemRepository.findAllByOrder(o));
//            }
//            return orderItems;
//        }
//        else if(active.equals("")){
//            orders = findOrders(customerEmail, "");
//            orders.forEach(order -> orderItems.addAll(orderItemRepository.findAllByOrder(order)));
//            return orderItems;
//        }
//        throw new ActiveNotValid("Active entered is not valid");
//    }

    public Order updateOrder(UUID orderNumber, Order order){
        Order existing = checkOrder(orderNumber);
        if(order.getOrderDate() != null){
            existing.setOrderDate(order.getOrderDate());
        }
        if(order.getTotal() != null){
            existing.setTotal(order.getTotal());
        }
        return orderRepository.save(existing);
    }

    public void deleteOrderItem(UUID orderItemNumber){
        OrderItem orderItem = checkOrderItem(orderItemNumber);
        orderItem.setShipped(true);
        orderItem.setShippingDate(Date.valueOf(LocalDate.now()));
        orderItemRepository.save(orderItem);
        checkOrderStatus(orderItem.getOrder().getOrderNumber());
    }

    public void deleteOrder(UUID orderNumber){
        Order order = checkOrder(orderNumber);
        order.setActive(false);
        orderRepository.save(order);
    }
    public void checkOrderStatus(UUID orderNumber){
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderOrderNumberAndShipped(orderNumber, false);
        if(orderItems.size() == 0){
            deleteOrder(orderNumber);
        }
    }

    public Order checkOrder(UUID orderNumber){

        Optional<Order> result = orderRepository.findByOrderNumber(orderNumber);
        if(!result.isPresent()){
            throw new OrderNumberNotFound("Order number not found");
        }
        return result.get();
    }
    public OrderItem checkOrderItem(UUID orderItemNumber){

        Optional<OrderItem> result = orderItemRepository.findByOrderItemNumber(orderItemNumber);
        if(!result.isPresent()){
            throw new OrderItemNumberNotFound("OrderItem number not found");
        }
        return result.get();
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }
}
