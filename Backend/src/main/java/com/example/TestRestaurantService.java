package com.example;

import java.util.List;

public class TestRestaurantService {
    public static void main(String[] args) {
        // Connexion à Fuseki et récupération des villes
        List<String> cities = RestaurantService.getCities();
        System.out.println("Villes disponibles : " + cities);

        // Exemple de récupération des restaurants dans une ville
        String selectedCity = cities.get(2); // Choisir la première ville de la liste
        System.out.println("Restaurants dans la ville de " + selectedCity + ":");

        List<Restaurant> restaurants = RestaurantService.getRestaurantByCity(selectedCity);
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }
    }
}
