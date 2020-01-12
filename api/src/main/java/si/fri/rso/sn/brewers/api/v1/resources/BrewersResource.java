package si.fri.rso.sn.brewers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
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
}
