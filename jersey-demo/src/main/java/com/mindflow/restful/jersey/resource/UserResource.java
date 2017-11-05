package com.mindflow.restful.jersey.resource;

import com.mindflow.restful.jersey.domain.User;
import com.mindflow.restful.jersey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/user")
public class UserResource {

    @Autowired
    private IUserService userService;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response createUser(User user){

        long id = userService.insert(user);

        return Response.status(Response.Status.CREATED)// 201
                .entity("A new user has been created")
                .header("Location",
                        "http://localhost:8888/user/"
                                + String.valueOf(id)).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.TEXT_HTML })
    public Response createUserApplicationFormURLencoded(
            @FormParam("name") String name,
            @FormParam("age") Integer age) {

        User user = new User();
        user.setName(name);
        user.setAge(age);
        long id = userService.insert(user);

        return Response
                .status(Response.Status.CREATED)// 201
                .entity("A new user/resource has been created at /user/"
                        + id)
                .header("Location",
                        "http://localhost:8888/user/"
                                + String.valueOf(id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") Long id) {

        return userService.findUserById(id);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList() {

        return userService.getUserList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByName(@QueryParam("username") String username) {

        return userService.findUserByName(username);
    }

    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response putUserById(@PathParam("id") Long id, User user){

        User userById = userService.findUserById(id);

        if (userById == null) {
            // resource not existent yet, and should be created under the specified URI
            long userId = userService.insert(user);
            return Response
                    .status(Response.Status.CREATED)
                    .entity("A new user has been created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/user/"
                                    + String.valueOf(userId)).build();
        } else {
            // resource is existent and a full update should occur
            userService.update(user);
            return Response
                    .status(Response.Status.OK)
                    .entity("The user you specified has been fully updated created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/user/"
                                    + String.valueOf(id)).build();
        }
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteUser() {
        userService.delete();
        return Response.status(Response.Status.NO_CONTENT)
                .entity("All users have been successfully removed").build();
    }

    @DELETE
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    public Response deleteUserById(@PathParam("id") Long id) {
        userService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT)
                .entity("User successfully removed from database").build();
    }

}
