package se.sina.webshop.resource.mapper;

import se.sina.webshop.service.exception.EmployeeExceptions.EmployeeUsernameError;
import se.sina.webshop.service.exception.EmployeeExceptions.InvalidEmployeeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class InvalidEmployeeMapper implements ExceptionMapper<InvalidEmployeeException>{

    @Override
    public Response toResponse(InvalidEmployeeException exception) {
        if (exception instanceof EmployeeUsernameError)
            return Response.status(NOT_ACCEPTABLE).entity(singletonMap("Error", exception.getMessage())).build();

        return Response.serverError().build();
    }
}
