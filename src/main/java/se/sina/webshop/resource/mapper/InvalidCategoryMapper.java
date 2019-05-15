package se.sina.webshop.resource.mapper;

import se.sina.webshop.model.entity.Category;
import se.sina.webshop.service.exception.CategoryExceptions.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidCategoryMapper implements ExceptionMapper<InvalidCategoryException>{

    @Override
    public Response toResponse(InvalidCategoryException exception) {
        if (exception instanceof CategoryNumberNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CategoryNameNotFound){
            return Response.status(NOT_FOUND).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CategoryUndeletable){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }
        if (exception instanceof CategoryNameAlreadyExists){
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();
        }

        return Response.serverError().build();
    }
}
