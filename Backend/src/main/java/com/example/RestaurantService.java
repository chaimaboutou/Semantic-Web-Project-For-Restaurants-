package com.example;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour interagir avec les données RDF sur Fuseki.
 */
public class RestaurantService {
    private static final String FUSEKI_SERVER = "http://localhost:3030/datasetRestaurant";
    private static final String PREFIXES = "PREFIX lc: <http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n";

    // Connexion à Fuseki
    private static RDFConnection createQueryConnection() {
        return RDFConnectionFuseki.create()
                .destination(FUSEKI_SERVER + "/query")
                .build();
    }

    /**
     * Récupère toutes les villes avec un restaurant dans la base RDF.
     * 
     * @return Liste des noms de villes.
     */
    public static List<String> getCities() {
        List<String> cities = new ArrayList<>();
        String queryStr = PREFIXES +
                "SELECT DISTINCT ?city WHERE {\n" +
                "  ?restaurant lc:hasCity ?city .\n" +
                "}";

        Query query = QueryFactory.create(queryStr);

        try (RDFConnection conn = createQueryConnection()) {
            conn.queryResultSet(query, results -> {
                while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    RDFNode cityNode = soln.get("city");
                    if (cityNode != null) {
                        cities.add(extractLocalName(cityNode.toString())); // Utilisation de la méthode d'extraction
                    }
                }
            });
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des villes : " + e.getMessage());
        }

        return cities;
    }

    /**
     * Extrait la partie locale d'un IRI.
     *
     * @param iri L'IRI complet.
     * @return La partie locale de l'IRI.
     */
    private static String extractLocalName(String iri) {
        if (iri == null || !iri.contains("#")) {
            return iri;
        }
        return iri.substring(iri.indexOf("#") + 1);
    }

    /**
     * Récupère les restaurants d'une ville donnée.
     * 
     * @param city Nom de la ville.
     * @return Liste des restaurants associés à cette ville.
     * @throws IllegalArgumentException Si le paramètre est invalide.
     */
    public static List<Restaurant> getRestaurantByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Le paramètre 'city' ne peut pas être nul ou vide.");
        }

        List<Restaurant> restaurants = new ArrayList<>();
        String escapedCity = escape(city);

        // Associer les villes à leurs IRI
        String cityIRI = getCityIRI(city);
        if (cityIRI == null) {
            System.err.println("Aucune ville trouvée pour: " + city);
            return restaurants;
        }

        StringBuilder queryBuilder = new StringBuilder();
       
        queryBuilder.append(PREFIXES);
        queryBuilder.append("SELECT ?titre ?description ?city ?address ?image ?services ?amenity WHERE {\n");
        queryBuilder.append("  ?restaurant lc:hasCity ?city .\n"); // Clause pour lier le restaurant à sa ville
        queryBuilder.append("  ?restaurant lc:hasCity <").append(cityIRI).append("> ;\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:photo ?image . }\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:titre ?titre . }\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:description ?description . }\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:adresse ?address . }\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:hasService ?services . }\n");
        queryBuilder.append("  OPTIONAL { ?restaurant lc:hasAmenity ?amenity . }\n");
        queryBuilder.append("}");

        Query query = QueryFactory.create(queryBuilder.toString());

        try (RDFConnection conn = createQueryConnection()) {
            conn.queryResultSet(query, results -> {
                int count = 0;
                while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    Restaurant restaurant = new Restaurant();

                    restaurant.setTitre(getValueOrDefault(soln, "titre"));
                    restaurant.setDescription(getValueOrDefault(soln, "description"));
                    restaurant.setAdresse(getValueOrDefault(soln, "address"));
                    restaurant.setPhoto(getValueOrDefault(soln, "image"));
                    restaurant.setServices(extractLocalName(getValueOrDefault(soln, "services"))); // Extraction du nom du service
                    restaurant.setAmenities(extractLocalName(getValueOrDefault(soln, "amenity")));
                    restaurant.setCity(extractLocalName(getValueOrDefault(soln, "city")));

                    restaurants.add(restaurant);
                    count++;
                }

                // Débogage : Affichage du nombre de restaurants récupérés
                System.out.println("Nombre de restaurants trouvés pour la ville " + city + ": " + count);
            });
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des restaurants : " + e.getMessage());
        }

        return restaurants;
    }
    
    public static List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String queryStr = PREFIXES +
                "SELECT ?titre ?description ?city ?address ?image ?services ?amenity WHERE {\n" +
                "  ?restaurant lc:hasCity ?city .\n" +
                "  OPTIONAL { ?restaurant lc:photo ?image . }\n" +
                "  OPTIONAL { ?restaurant lc:titre ?titre . }\n" +
                "  OPTIONAL { ?restaurant lc:description ?description . }\n" +
                "  OPTIONAL { ?restaurant lc:adresse ?address . }\n" +
                "  OPTIONAL { ?restaurant lc:hasService ?services . }\n" +
                "  OPTIONAL { ?restaurant lc:hasAmenity ?amenity . }\n" +
                "}";

        Query query = QueryFactory.create(queryStr);

        try (RDFConnection conn = createQueryConnection()) {
            conn.queryResultSet(query, results -> {
                while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    Restaurant restaurant = new Restaurant();

                    restaurant.setTitre(getValueOrDefault(soln, "titre"));
                    restaurant.setDescription(getValueOrDefault(soln, "description"));
                    restaurant.setAdresse(getValueOrDefault(soln, "address"));
                    restaurant.setPhoto(getValueOrDefault(soln, "image"));
                    restaurant.setServices(extractLocalName(getValueOrDefault(soln, "services")));
                    restaurant.setAmenities(extractLocalName(getValueOrDefault(soln, "amenity")));
                    restaurant.setCity(extractLocalName(getValueOrDefault(soln, "city")));

                    restaurants.add(restaurant);
                }
            });
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de tous les restaurants : " + e.getMessage());
        }

        return restaurants;
    }


    /**
     * Méthode utilitaire pour obtenir l'IRI d'une ville.
     * 
     * @param city Nom de la ville.
     * @return L'IRI complet de la ville ou null si la ville n'est pas trouvée.
     */
    private static String getCityIRI(String city) {
        // Vérifier que la ville n'est pas nulle ou vide
        if (city == null || city.trim().isEmpty()) {
            return null; // Retourne null si la ville est invalide
        }
        
        // Base IRI
        String baseIRI = "http://www.semanticweb.org/BoutouNaji/ontologies/2024/9/ProjetRestaurant#";

        // Construire l'IRI dynamiquement
        return baseIRI + city.trim().replace(" ", "-");
    }


    /**
     * Méthode utilitaire pour échapper les caractères spéciaux dans le texte.
     * 
     * @param input Texte brut.
     * @return Texte échappé.
     */
    private static String escape(String input) {
        if (input == null)
            return "";
        return input.replace("\"", "\\\"");
    }

    /**
     * Méthode utilitaire pour obtenir la valeur d'un RDFNode.
     * 
     * @param soln Solution de requête.
     * @param var  Nom de la variable.
     * @return La valeur ou une chaîne vide si non définie.
     */
    private static String getValueOrDefault(QuerySolution soln, String var) {
        return soln.contains(var) ? soln.get(var).toString() : "";
    }
}
