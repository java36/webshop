package se.sina.webshop.resource;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.conversion.Converter;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.model.web.BrandWeb;
import se.sina.webshop.model.web.CategoryWeb;
import se.sina.webshop.resource.authentication.Secured;
import se.sina.webshop.service.BrandService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Component
@Path("brands")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class BrandResource {
    private final BrandService brandService;
    private final Converter converter;

    @Context
    private UriInfo uriInfo;

    public BrandResource(BrandService brandService, Converter converter) {
        this.brandService = brandService;
        this.converter = converter;
    }

    @POST
    @Secured
    public Response createBrand(BrandWeb brandWeb){
        Brand result = brandService.createBrand(converter.convertFrom(brandWeb));
        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getBrandNumber().toString())
                .toString()))
                .build();
    }

    @GET
    public Response getBrands(@BeanParam Queries queries) {
        return Response.ok(converter.convertBrandList(brandService.find(queries.getName(), queries.getCategory(), queries.getActive()))).build();
    }

    @GET
    @Path("{number}")
    public Response getByBrandNumber(@PathParam("number") UUID brandNumber) {
        return Response.ok(converter.convertFrom(brandService.find(brandNumber))).build();
    }

    @PUT
    @Secured
    @Path("{number}")
    public Response updateBrand(@PathParam("number") UUID number, BrandWeb brandWeb) {
        Category category = new Category(null, null);
        brandService.update(number, converter.convertFrom(brandWeb), brandWeb.getCategory() == null ?  category : converter.convertFrom(brandWeb.getCategory()));
        return Response.noContent().build();
    }

    @DELETE
    @Secured
    @Path("{number}")
    public Response deleteBrand(@PathParam("number") UUID brandNumber) {
        brandService.delete(brandNumber);
        return Response.noContent().build();
    }

}
