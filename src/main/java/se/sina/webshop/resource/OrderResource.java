package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.*;
import se.sina.webshop.model.web.ModelWeb;
import se.sina.webshop.model.web.OrderItemWeb;
import se.sina.webshop.model.web.OrderWeb;
import se.sina.webshop.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("orders")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class OrderResource {

    private final OrderService orderService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public OrderResource(OrderService orderService, Converter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

    @POST
    public Response createOrder(OrderWeb orderWeb){
        Order order = orderService.createOrder(converter.convertFrom(orderWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(order.getOrderNumber().toString())
                .toString()))
                .build();
    }

    @POST
    @Path("{orderNumber}")
    public Response createOrderItem(@PathParam("orderNumber") UUID orderNumber, OrderItemWeb orderItemWeb){
        OrderItem orderItem = orderService.createOrderItem(orderNumber, orderItemWeb.getItem().getItemNumber());
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(orderItem.getOrderItemNumber().toString())
                .toString()))
                .build();
    }
    @GET
    public Response getOrders(@BeanParam Queries queries) {
            List<OrderWeb> orderWebs = converter.convertOrderList(orderService.findOrders(queries.getCustomerEmail(), queries.getActive()));
            return Response.ok(orderWebs).build();
    }

    @GET
    @Path("{number}")
    public Response getOrderByNumber(@PathParam("number") UUID orderNumber) {
        return Response.ok(converter.convertFrom(orderService.findOrderByNumber(orderNumber))).build();
    }

    @GET
    @Path("{orderNumber}/orderItems/{number}")
    public Response getOrderItemByNumber(@PathParam("number") UUID orderItemNumber) {
        return Response.ok(converter.convertFrom(orderService.findOrderItemByNumber(orderItemNumber))).build();
    }
    @GET
    @Path("{orderNumber}/orderItems")
    public Response getOrderItems(@PathParam("orderNumber") UUID orderNumber, @BeanParam Queries queries) {
        List<OrderItemWeb> orderItemWebs = converter.convertOrderItemList(orderService.findOrderItems(orderNumber, queries.getCustomerEmail(), queries.getActive()));
        return Response.ok(orderItemWebs).build();
    }
    @PUT
    @Path("{number}")
    public Response updateOrder(@PathParam("number") UUID number, OrderWeb orderWeb) {
        Customer customer = new Customer(null, null, null, null, null);
        orderService.updateOrder(number, converter.convertFrom(orderWeb));
        return Response.noContent().build();
    }
    @DELETE
    @Path("{number}")
    public Response deleteOrder(@PathParam("number") UUID orderNumber) {
        orderService.deleteOrder(orderNumber);
        return Response.noContent().build();
    }
    @DELETE
    @Path("{orderNumber}/orderItems/{number}")
    public Response deleteOrderItem(@PathParam("number") UUID orderItemNumber) {
        orderService.deleteOrderItem(orderItemNumber);
        return Response.noContent().build();
    }
}
