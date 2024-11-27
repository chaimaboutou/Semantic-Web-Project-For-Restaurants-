package com.example;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Permet toutes les origines, à modifier selon vos besoins
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        
        // Permet les méthodes HTTP autorisées
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Permet les en-têtes spécifiques dans les requêtes
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");

        // Si la méthode est OPTIONS, répondre avec un statut 200 OK pour les requêtes de pré-vol
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
            responseContext.setStatus(200);
        }
    }
}
