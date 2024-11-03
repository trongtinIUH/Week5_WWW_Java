package trantrongtin_iuh.baitap4_payara.backend.api;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import trantrongtin_iuh.baitap4_payara.backend.business.UserBeanRemote;
import trantrongtin_iuh.baitap4_payara.backend.dtos.UserDTO;
import trantrongtin_iuh.baitap4_payara.backend.repositories.entity.User;

@Path("/users")
public class UserResource {

    @EJB
    private UserBeanRemote userBeanRemote;

    @GET
    public Response getUsers() {
        return Response.ok(userBeanRemote.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        return Response.ok(userBeanRemote.getUser(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) {
        try {
            User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(),
                    userDTO.getPassword(), userDTO.getEmail(), userDTO.getFacebook(), userDTO.getBio());

            userBeanRemote.add(user);
        } catch (Exception e) {
            System.out.println("Error in addUser" + e);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        try {
            User user = userBeanRemote.getUser(id);
            if (user != null) {
                userBeanRemote.delete(user);
            }
        } catch (Exception e) {
            System.out.println("Error in deleteUser" + e);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO) {
        try {
            User user = userBeanRemote.getUser(userDTO.getId());
            if (user != null) {
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setUsername(userDTO.getUsername());
                user.setPassword(userDTO.getPassword());
                user.setEmail(userDTO.getEmail());
                user.setFacebook(userDTO.getFacebook());
                user.setBio(userDTO.getBio());
                userBeanRemote.update(user);
            }
        } catch (Exception e) {
            System.out.println("Error in updateUser" + e);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }




}