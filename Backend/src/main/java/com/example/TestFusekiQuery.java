package com.example;




import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.*;
import org.apache.jena.update.*;
import org.apache.jena.rdf.model.*;

public class TestFusekiQuery {

    private static final String UPDATE_SERVICE_URL = "http://localhost:3030/dbprojetWS/update"; // URL de mise à jour Fuseki
    private static final String QUERY_SERVICE_URL = "http://localhost:3030/dbprojetWS/query"; // URL de la requête Fuseki

    public static void main(String[] args) {
        // Ajouter un triplet
        String subject = "http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#Q53845265";
        String predicate = "http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#hasAmenity";
        String object = "http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#HomeDelivery";

        // 1. Ajouter un triplet
        //addTriplet(subject, predicate, object);

        // 2. Vérifier la mise à jour avec la requête COUNT
        String countQuery = "SELECT (COUNT(*) AS ?count) WHERE { ?s ?p ?o }";
        runCountQuery(countQuery);

        // 3. Supprimer un triplet
        //deleteTriplet(subject, predicate, object);

        // 4. Vérifier à nouveau la mise à jour avec la requête COUNT
       
       runCountQuery(countQuery);
    }

    // Fonction pour ajouter un triplet avec INSERT DATA
    public static void addTriplet(String subject, String predicate, String object) {
        String updateStr = "PREFIX lc: <http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#>\r\n"
                + "INSERT DATA {\r\n"
                + "  <" + subject + "> a lc:Restaurant ;\r\n"
                + "  lc:hasAmenity <" + object + "> .\r\n"
                + "}";

        runUpdate(updateStr);
    }

    // Fonction pour supprimer un triplet avec DELETE DATA
    public static void deleteTriplet(String subject, String predicate, String object) {
        String updateStr = "DELETE DATA {\r\n"
                + "  <" + subject + "> <" + predicate + "> <" + object + "> .\r\n"
                + "}";

        runUpdate(updateStr);
    }

    // Fonction pour exécuter une requête d'update (INSERT ou DELETE)
    private static void runUpdate(String updateStr) {
        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) RDFConnectionFuseki.create().destination(UPDATE_SERVICE_URL).build()) {
            UpdateRequest request = UpdateFactory.create(updateStr);
            conn.update(request);
            System.out.println("Mise à jour effectuée !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction pour exécuter une requête COUNT pour compter le nombre de triplets
    private static void runCountQuery(String queryStr) {
        try (RDFConnectionFuseki conn = (RDFConnectionFuseki) RDFConnectionFuseki.create().destination(QUERY_SERVICE_URL).build()) {
            Query query = QueryFactory.create(queryStr);
            try (QueryExecution qExec = conn.query(query)) {
                ResultSet results = qExec.execSelect();
                if (results.hasNext()) {
                    QuerySolution sol = results.next();
                    RDFNode count = sol.get("count");
                    System.out.println("Nombre de triplets : " + count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
