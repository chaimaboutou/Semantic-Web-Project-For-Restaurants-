package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import java.util.List;

@Path("/restaurant")  // Chemin de l'API, dans ce cas "/api/hospital"
public class RestaurantController {

    @GET
    @Path("/cities")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<String> getCities() {
        return RestaurantService.getCities();
    }
    
    @GET
    @Path("/restaurants")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<Restaurant> getAllRestaurants() {
        return RestaurantService.getAllRestaurants();
    }

    @GET
    @Path("/byCity")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<Restaurant> getRestaurantsByCity(@QueryParam("city") String city) {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("Le paramètre 'city' est requis.");
        }
        return RestaurantService.getRestaurantByCity(city);
    }

}
