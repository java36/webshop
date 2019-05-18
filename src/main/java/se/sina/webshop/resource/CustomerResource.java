package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Customer;
import se.sina.webshop.model.web.CategoryWeb;
import se.sina.webshop.model.web.CustomerWeb;
import se.sina.webshop.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("customers")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class CustomerResource {

    private final CustomerService customerService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public CustomerResource(CustomerService customerService, Converter converter) {
        this.customerService = customerService;
        this.converter = converter;
    }

    @POST
    public Response createCustomer(CustomerWeb customerWeb){
        Customer result = customerService.createCustomer(converter.convertFrom(customerWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getCustomerNumber().toString())
                .toString()))
                .build();
    }

    @GET
    public Response getCustomers(@BeanParam Queries queries){
        return Response.ok(converter.convertCustomerList(customerService.find(queries.getCustomerEmail(), queries.getActive()))).build();
    }

    @GET
    @Path("{number}")
    public Response getCustomerByNumber(@PathParam("number") UUID customerNumber){
        return Response.ok(converter.convertFrom(customerService.find(customerNumber))).build();
    }

    @PUT
    @Path("{number}")
    public Response updateCustomer(@PathParam("number") UUID customerNumber, CustomerWeb customerWeb){
        customerService.update(customerNumber, converter.convertFrom(customerWeb));
        return Response.noContent().build();
    }

    @DELETE
    @Path("{number}")
    public Response deleteCustomer(@PathParam("number") UUID number){
        customerService.delete(number);
        return Response.noContent().build();
    }
}
