package si.fri.rso.sn.brewers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.sn.brewers.models.dtos.Beer;
import si.fri.rso.sn.brewers.models.entities.Brewer;
import si.fri.rso.sn.brewers.services.beans.BrewersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Log
@ApplicationScoped
@Path("/brewers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BrewersResource {

    @Inject
    private BrewersBean bean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getBrewers() {
        List<Brewer> items = bean.getBrewers();
        return Response.ok(items).build();
    }

    @POST
    public Response createBrewer(Brewer brewer) {
        if (brewer.getName() == null || brewer.getCountry() == null || brewer.getEstablished() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            brewer = bean.createBrewer(brewer);
        }
        //Handle success/failure
        if (brewer.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(brewer).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(brewer).build();
        }
    }

    @DELETE
    @Path("{brewerId}")
    public Response deleteBrewer(@PathParam("brewerId") int brewerId) {
        boolean deleted = bean.deleteBrewer(brewerId);
        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{breweryId}/beers")
    public Response getBeers(@PathParam("breweryId") Integer breweryId) {

        List<Beer> beers;
        beers = bean.getBeers(breweryId);

        if (beers == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(beers).build();
    }
}
