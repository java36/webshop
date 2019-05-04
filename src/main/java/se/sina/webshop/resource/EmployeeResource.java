package se.sina.webshop.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("employees")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class EmployeeResource {
}
