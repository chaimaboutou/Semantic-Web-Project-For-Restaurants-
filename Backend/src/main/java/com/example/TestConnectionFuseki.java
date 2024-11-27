package com.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.atlas.lib.StrUtils;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.update.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.*;

public class TestConnectionFuseki {
    public static void main(String[] args) {
        // URL du serveur Fuseki
        String fusekiEndpoint = "http://localhost:3030/#/dataset/dbprojetWS/query";  // Remplacez avec l'URL de votre instance Fuseki et dataset

        // Requête SPARQL
        String sparqlQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o } LIMIT 10";

        // Connexion au serveur Fuseki
        try (RDFConnection conn = RDFConnectionFactory.connect(fusekiEndpoint)) {
            // Exécution de la requête SPARQL
            try (QueryExecution qExec = conn.query(sparqlQuery)) {
                ResultSet results = qExec.execSelect();

                // Parcours et affichage des résultats
                ResultSetFormatter.out(System.out, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





