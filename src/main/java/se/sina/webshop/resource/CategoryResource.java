package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.model.web.CategoryWeb;
import se.sina.webshop.resource.authentication.Secured;
import se.sina.webshop.service.CategoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("categories")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class CategoryResource {

    private final CategoryService categoryService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public CategoryResource(CategoryService categoryService, Converter converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @POST
    @Secured
    public Response createCategory(CategoryWeb categoryWeb){
        Category result = categoryService.createCategory(converter.convertFrom(categoryWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getCategoryNumber().toString())
                .toString()))
                .build();
    }

    @GET
    public Response getCategories(@BeanParam Queries queries){
        return Response.ok(converter.convertCategoryList(categoryService.find(queries.getName(), queries.getActive()))).build();
    }

    @GET
    @Path("{number}")
    public Response getCategoryByNumber(@PathParam("number") UUID categoryNumber){
        return Response.ok(converter.convertFrom(categoryService.find(categoryNumber))).build();
    }

    @PUT
    @Secured
    @Path("{number}")
    public Response updateCategory(@PathParam("number") UUID categoryNumber, CategoryWeb categoryWeb){
        categoryService.update(categoryNumber, converter.convertFrom(categoryWeb));
        return Response.noContent().build();
    }

    @DELETE
    @Secured
    @Path("{number}")
    public Response deleteCategory(@PathParam("number") UUID number){
        categoryService.delete(number);
        return Response.noContent().build();
    }
}
