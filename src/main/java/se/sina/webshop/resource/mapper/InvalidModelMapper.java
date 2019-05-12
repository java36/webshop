package se.sina.webshop.resource.mapper;

import se.sina.webshop.service.exception.BrandExceptions.BrandNameNotFound;
import se.sina.webshop.service.exception.BrandExceptions.BrandNumberNotFound;
import se.sina.webshop.service.exception.BrandExceptions.InvalidBrandException;
import se.sina.webshop.service.exception.ModelExceptions.InvalidModelException;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidModelMapper implements ExceptionMapper<InvalidModelException> {


    @Override
    public Response toResponse(InvalidModelException exception) {
        if (exception instanceof ModelNameNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof ModelNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
