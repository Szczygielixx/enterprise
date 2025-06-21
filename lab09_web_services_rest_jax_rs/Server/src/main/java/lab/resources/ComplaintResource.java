package lab.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;
import lab.services.ComplaintService;

import java.util.List;

@Path("complaints")
@RequestScoped
public class ComplaintResource {

    @Inject
    private ComplaintService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComplaintDTO> getAllComplaints(@QueryParam("status") String status) {
        return service.findAll(status);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ComplaintDTO getComplaint(@PathParam("id") Long id) {
        return service.find(id);
    }

    @GET
    @Path("{id}/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkStatus(@PathParam("id") Long id) {
        return service.find(id).getStatus();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postComplaint(ComplaintDTO complaint) {
        service.create(complaint);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putComplaint(@PathParam("id") Long id, ComplaintDTO complaint) {
        complaint.setId(id); // Make sure the ID from path is set in the DTO
        service.edit(complaint);
    }

    @DELETE
    @Path("{id}")
    public void deleteComplaint(@PathParam("id") Long id) {
        service.remove(service.find(id));
    }
} 