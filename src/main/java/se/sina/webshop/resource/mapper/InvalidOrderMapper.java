package se.sina.webshop.resource.mapper;

import se.sina.webshop.service.exception.BrandExceptions.*;
import se.sina.webshop.service.exception.OrderExceptions.InvalidOrderException;
import se.sina.webshop.service.exception.OrderExceptions.OrderItemNumberNotFound;
import se.sina.webshop.service.exception.OrderExceptions.OrderNumberNotFound;
import se.sina.webshop.service.exception.OrderExceptions.OrderUndeletable;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidOrderMapper implements ExceptionMapper<InvalidOrderException> {


    @Override
    public Response toResponse(InvalidOrderException exception) {
        if (exception instanceof OrderNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof OrderItemNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof OrderUndeletable){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }


        return Response.serverError().build();
    }
}
