package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.model.web.CategoryWeb;
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

    private CategoryService categoryService;
    private Converter converter;

    @Context
    private UriInfo uriInfo;

    public CategoryResource(CategoryService categoryService, Converter converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @POST
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
//        if(!queries.getName().equals("")){
//            List<CategoryWeb> categoryWebs = converter.convertCategoryList(categoryService.find(queries.getName(), "true"));
//            if(categoryWebs.size() > 0){
//                return Response.ok(categoryWebs).build();
//            }
//            return Response.noContent().build();
//        }
//        else if(queries.getActive().equals("false")){
//            return Response.ok(converter.convertCategoryList(categoryService.find("", "false"))).build();
//        }
//
//        return Response.ok(converter.convertCategoryList(categoryService.find("", ""))).build();
        return Response.ok(converter.convertCategoryList(categoryService.find(queries.getName(), queries.getActive()))).build();
    }

    @GET
    @Path("{number}")
    public Response getCategoryByNumber(@PathParam("number") UUID categoryNumber){
        return Response.ok(converter.convertFrom(categoryService.find(categoryNumber))).build();
    }

    @PUT
    @Path("{number}")
    public Response updateCategory(@PathParam("number") UUID categoryNumber, CategoryWeb categoryWeb){
        categoryService.update(categoryNumber, converter.convertFrom(categoryWeb));
        return Response.noContent().build();
    }

//    @DELETE
//    @Path("{number}")
//    public Response deleteCategory(@PathParam("number") UUID number){
//
//    }
}
