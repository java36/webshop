package se.sina.webshop.resource.mapper;

import se.sina.webshop.model.entity.Customer;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNameNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNumberNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryUndeletable;
import se.sina.webshop.service.exception.CategoryExceptions.InvalidCategoryException;
import se.sina.webshop.service.exception.CustomerExceptions.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidCustomerMapper implements ExceptionMapper<InvalidCustomerException> {
    @Override
    public Response toResponse(InvalidCustomerException exception) {
        if (exception instanceof CustomerNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CustomerNameNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CustomerNameNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof EmailAlreadyExists){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CustomerEmailNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
