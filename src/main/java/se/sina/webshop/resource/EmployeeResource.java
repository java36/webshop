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

    private final EmployeeService employeeService;
    private final Converter converter;

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
    public Response getEmployees(@BeanParam Queries queries){
        return Response.ok(converter.convertEmployeeList(employeeService.find(queries.getUsername(), queries.getActive()))).build();
    }
    @GET
    @Path("{number}")
    public Response getByNum(@PathParam("number") UUID number){
        return Response.ok(converter.convertFrom(employeeService.find(number))).build();
    }

    @PUT
    @Path("{number}")
    public Response updateEmployee(@PathParam("number") UUID number, Employee employee){
        employeeService.update(number, employee);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{number}")
    public Response deleteEmployee(@PathParam("number") UUID number){
        employeeService.delete(number);
        return Response.noContent().build();
    }
}
