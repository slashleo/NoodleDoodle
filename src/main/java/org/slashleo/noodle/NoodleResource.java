package org.slashleo.noodle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
@ApplicationScoped
public class NoodleResource {

    @Inject
    NoodleDaoImpl noodleDao;

    /**
     * Base URI of NoodleResource.
     */
    private static final String BASE_URI = "http://localhost:8080/";

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEndpoint(final NoodleDto noodleDto){
        NoodleDto newNoodleDto = new NoodleDto();
        newNoodleDto.setName(noodleDto.getName());
        newNoodleDto.setFood(noodleDto.getFood());
        newNoodleDto.setVegan(noodleDto.getVegan());

        NoodleDto createdNoodle = noodleDao.create(newNoodleDto);

        return Response.created(URI.create(BASE_URI + createdNoodle.getId())).entity(createdNoodle).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEndpoint(final @PathParam("id") int id) {
        return Response.ok().entity(noodleDao.read(id)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAllEndpoint() {
        return Response.ok().entity(noodleDao.readAll()).build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEndpoint(final NoodleDto noodleDto) {
        return Response.ok().entity(noodleDao.update(noodleDto)).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEndpoint(final NoodleDto noodleDto) {
        noodleDao.delete(noodleDto);
        return Response.noContent().build();
    }
}