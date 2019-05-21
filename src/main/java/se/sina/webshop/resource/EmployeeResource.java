package se.sina.webshop.resource;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Employee;
import se.sina.webshop.model.web.EmployeeWeb;
import se.sina.webshop.resource.authentication.Secured;
import se.sina.webshop.resource.authentication.SingleGenerator;
import se.sina.webshop.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("employees")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class EmployeeResource {

    private final static SingleGenerator singleKey = SingleGenerator.getInstance();
    private final EmployeeService employeeService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public EmployeeResource(EmployeeService employeeService, Converter converter) {
        this.employeeService = employeeService;
        this.converter = converter;
    }

    @POST
    @Secured
    public Response poseEmployee(EmployeeWeb employeeWeb){

        Employee employee = employeeService.createEmployee(converter.convertFrom(employeeWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(employee.getEmployeeNumber().toString())
                .toString()))
                .build();
    }
    @GET
    @Secured
    public Response getEmployees(@BeanParam Queries queries){
        return Response.ok(converter.convertEmployeeList(employeeService.find(queries.getUsername(), queries.getActive()))).build();
    }
    @GET
    @Secured
    @Path("{number}")
    public Response getByNum(@PathParam("number") UUID number){
        return Response.ok(converter.convertFrom(employeeService.find(number))).build();
    }

    @PUT
    @Secured
    @Path("{number}")
    public Response updateEmployee(@PathParam("number") UUID number, Employee employee){
        employeeService.update(number, employee);
        return Response.noContent().build();
    }

    @DELETE
    @Secured
    @Path("{number}")
    public Response deleteEmployee(@PathParam("number") UUID number){
        employeeService.delete(number);
        return Response.noContent().build();
    }

    @POST
    @Path("login")
    public Response authenticate(EmployeeWeb employeeWeb){
        EmployeeWeb result = converter.convertFrom(employeeService.authenticate(employeeWeb.getUsername(), employeeWeb.getPassword()));
        String token = issueToken(employeeWeb.getUsername());
        return Response.ok(result).header(AUTHORIZATION, "Bearer " + token).build();
    }

    private String issueToken(String username) {
        Key key = singleKey.getGenerator();

        String token = Jwts.builder()
                .setSubject("")
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDate.now().plus(1, ChronoUnit.DAYS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key, SignatureAlgorithm.HS256).compact();
        return token;
    }
}
