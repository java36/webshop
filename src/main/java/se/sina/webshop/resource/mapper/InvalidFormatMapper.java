package se.sina.webshop.resource.mapper;

import se.sina.webshop.service.exception.BrandExceptions.*;
import se.sina.webshop.service.exception.FormatExceptions.ActiveNotValid;
import se.sina.webshop.service.exception.FormatExceptions.EmailFormatException;
import se.sina.webshop.service.exception.FormatExceptions.InvalidFormatException;
import se.sina.webshop.service.exception.FormatExceptions.NameFormatException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Provider
public class InvalidFormatMapper implements ExceptionMapper<InvalidFormatException> {


    @Override
    public Response toResponse(InvalidFormatException exception) {
        if (exception instanceof EmailFormatException){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof NameFormatException){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof ActiveNotValid){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
