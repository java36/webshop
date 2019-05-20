package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Item;
import se.sina.webshop.model.entity.Model;
import se.sina.webshop.model.web.ItemWeb;
import se.sina.webshop.model.web.ModelWeb;
import se.sina.webshop.resource.authentication.Secured;
import se.sina.webshop.service.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("items")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class ItemResource {

    private final ItemService itemService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public ItemResource(ItemService itemService, Converter converter) {
        this.itemService = itemService;
        this.converter = converter;
    }

    @POST
    @Secured
    public Response createItem(ItemWeb itemWeb){
        Item result = itemService.createItem(converter.convertFrom(itemWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getItemNumber().toString())
                .toString()))
                .build();
    }

    @GET
    public Response getItems(@BeanParam Queries queries) {
        return Response.ok(converter.convertItemList(itemService.find(queries.getModel(), queries.getItemStatus()))).build();
    }

    @GET
    @Secured
    @Path("{number}")
    public Response getByItemNumber(@PathParam("number") UUID itemNumber) {
        return Response.ok(converter.convertFrom(itemService.find(itemNumber))).build();
    }

    @PUT
    @Secured
    @Path("{number}")
    public Response updateItem(@PathParam("number") UUID number, ItemWeb itemWeb) {
        Model model = new Model(null, null, null, null, null);
        itemService.update(number, converter.convertFrom(itemWeb), itemWeb.getModel() == null ?  model : converter.convertFrom(itemWeb.getModel()));
        return Response.noContent().build();
    }

    @DELETE
    @Secured
    @Path("{number}")
    public Response deleteItem(@PathParam("number") UUID itemNumber){
        itemService.delete(itemNumber);
        return Response.noContent().build();
    }
}
