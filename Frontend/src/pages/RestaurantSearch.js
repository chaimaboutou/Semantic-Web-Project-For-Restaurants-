import React, { useState, useEffect } from "react";
import CitySelector from "../components/CitySelector";
import RestaurantList from "../components/RestaurantList";
import { fetchCities, fetchRestaurantsByCity, fetchAllrestaurants } from "../services/api";
import Navbar from "../components/Navbar"; // Importez le composant Navbar
import './RestaurantSearch.css';

const RestaurantSearch = () => {
    const [cities, setCities] = useState([]);
    const [selectedCity, setSelectedCity] = useState("");
    const [restaurants, setRestaurants] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadCities = async () => {
            setLoading(true);
            try {
                const citiesData = await fetchCities();
                setCities(citiesData);
            } catch (error) {
                console.error("Erreur lors du chargement des villes :", error);
                setError("Erreur lors du chargement des villes.");
            } finally {
                setLoading(false);
            }
        };

        loadCities();
    }, []);


    const handleCityChange = async (event) => {
        const city = event.target.value;
        setSelectedCity(city);
        setRestaurants([]);
        setError(null);

        setLoading(true);
        try {
            if (city === "All") {
                // Lorsque "All" est sélectionné, récupérer tous les restaurants
                const restaurantsData = await fetchAllrestaurants();
                setRestaurants(restaurantsData);
            } else {
                // Sinon, récupérer les restaurants pour la ville sélectionnée
                const restaurantsData = await fetchRestaurantsByCity(city);
                setRestaurants(restaurantsData);
            }
        } catch (error) {
            setError("Erreur lors du chargement des restaurants.");
        } finally {
            setLoading(false);
        }
    };


    return (
        <div>
            <Navbar /> {/* Ajoutez la Navbar ici */}
            <h1>Chercher les restaurants selon la ville</h1>
            <CitySelector
                cities={cities}
                selectedCity={selectedCity}
                onCityChange={handleCityChange}
            />
            {selectedCity && (
                <div>
                    {loading && <p className="loading">Chargement des restaurants... Cela peut prendre un moment.</p>}
                    {error && <p className="error">{error}</p>}

                    {!loading && restaurants.length === 0 && (
                        <p className="no-results">Aucun restaurant trouvé pour cette ville.</p>
                    )}
                    {!loading && restaurants.length > 0 && (
                        <RestaurantList restaurants={restaurants} />
                    )}
                </div>
            )}
        </div>
    );
};

export default RestaurantSearch;
