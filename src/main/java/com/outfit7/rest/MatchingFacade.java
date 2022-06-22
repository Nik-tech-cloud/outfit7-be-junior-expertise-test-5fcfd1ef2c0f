package com.outfit7.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.outfit7.entity.User;
import com.outfit7.services.OpponentsService;

@Path("/matching")
public class MatchingFacade {

    @Inject
    OpponentsService opponentsService;

    @GET
    @Path("/classic/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> matchOpponents(@PathParam("userId") String userId) {
        return opponentsService.matchOpponents(userId);
    }

}