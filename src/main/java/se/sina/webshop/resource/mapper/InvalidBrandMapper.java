package se.sina.webshop.resource.mapper;

import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.service.exception.BrandExceptions.BrandNameNotFound;
import se.sina.webshop.service.exception.BrandExceptions.BrandNumberNotFound;
import se.sina.webshop.service.exception.BrandExceptions.InvalidBrandException;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNameNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.InvalidCategoryException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidBrandMapper implements ExceptionMapper<InvalidBrandException> {


    @Override
    public Response toResponse(InvalidBrandException exception) {
        if (exception instanceof BrandNameNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof BrandNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
