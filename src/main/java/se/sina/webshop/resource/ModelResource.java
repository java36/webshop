package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.model.entity.Model;
import se.sina.webshop.model.web.BrandWeb;
import se.sina.webshop.model.web.ModelWeb;
import se.sina.webshop.service.ModelService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("models")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class ModelResource {

    private ModelService modelService;
    private Converter converter;

    @Context
    private UriInfo uriInfo;

    public ModelResource(ModelService modelService, Converter converter) {
        this.modelService = modelService;
        this.converter = converter;
    }

    @POST
    public Response createModel(ModelWeb modelWeb){
        Model result = modelService.createModel(converter.convertFrom(modelWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getModelNumber().toString())
                .toString()))
                .build();
    }

    @GET
    public Response getModels(@BeanParam Queries queries) {
        return Response.ok(converter.convertModelList(modelService.find(queries.getName(), queries.getBrand(), queries.getActive()))).build();
    }

    @GET
    @Path("{number}")
    public Response getByModelNumber(@PathParam("number") UUID modelNumber) {
        return Response.ok(converter.convertFrom(modelService.find(modelNumber))).build();
    }

    @PUT
    @Path("{number}")
    public Response updateModel(@PathParam("number") UUID number, ModelWeb modelWeb) {
        Brand brand = new Brand(null, null, null);
        modelService.update(number, converter.convertFrom(modelWeb), modelWeb.getBrand() == null ?  brand : converter.convertFrom(modelWeb.getBrand()));
        return Response.noContent().build();
    }

    @DELETE
    @Path("{number}")
    public Response deleteModel(@PathParam("number") UUID modelNumber) {
        modelService.delete(modelNumber);
        return Response.noContent().build();
    }
}
