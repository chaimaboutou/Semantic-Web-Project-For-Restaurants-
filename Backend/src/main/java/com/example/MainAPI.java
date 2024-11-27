

package com.example;

import java.util.ArrayList;
import java.util.List;



import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class MainAPI {

    

    public static void main(String[] args) throws Exception {
       
        // Créez l'instance du serveur Jetty
        Server jettyServer = new Server(8080);

        // Créez un contexte de servlet
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");

        // Configurez Jersey avec ResourceConfig
        ResourceConfig resourceConfig = new ResourceConfig().packages("com.example");

        // Ajoutez le filtre CORS à la configuration de Jersey
        resourceConfig.register(CORSFilter.class);

        // Créez et configurez le ServletHolder pour le ServletContainer de Jersey
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        servletHolder.setInitOrder(0); // Assurez-vous qu'il s'initialise au démarrage

        // Ajoutez le servlet au contexte
        context.addServlet(servletHolder, "/api/*");

        // Définissez le contexte pour le serveur Jetty
        jettyServer.setHandler(context);

        // Démarrez le serveur Jetty
        jettyServer.start();
        jettyServer.join();
    }
}