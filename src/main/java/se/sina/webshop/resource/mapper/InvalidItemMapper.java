package se.sina.webshop.resource.mapper;

import se.sina.webshop.service.exception.ItemExceptions.InvalidItemException;
import se.sina.webshop.service.exception.ItemExceptions.ItemNumberNotFound;
import se.sina.webshop.service.exception.ModelExceptions.InvalidModelException;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidItemMapper implements ExceptionMapper<InvalidItemException> {


    @Override
    public Response toResponse(InvalidItemException exception) {
        if (exception instanceof ItemNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
