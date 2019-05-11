package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Employee;
import se.sina.webshop.model.web.EmployeeWeb;
import se.sina.webshop.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("employees")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class EmployeeResource {

    private EmployeeService employeeService;
    private Converter converter;

    @Context
    private UriInfo uriInfo;

    public EmployeeResource(EmployeeService employeeService, Converter converter) {
        this.employeeService = employeeService;
        this.converter = converter;
    }

    @POST
    public Response poseEmployee(EmployeeWeb employeeWeb){

        Employee employee = employeeService.createEmployee(converter.convertFrom(employeeWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(employee.getEmployeeNumber().toString())
                .toString()))
                .build();
    }
    @GET
    public Response getEmployees(){
        return Response.ok(converter.convertEmployeeList(employeeService.getEmployees())).build();
    }
    @GET
    @Path("{number}")
    public Response getByNum(@PathParam("number") UUID number){
        return Response.ok(converter.convertFrom(employeeService.find(number))).build();
    }

}
