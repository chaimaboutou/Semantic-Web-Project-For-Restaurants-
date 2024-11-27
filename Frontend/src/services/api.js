const BASE_URL = "http://localhost:8080/api/restaurant";

export const fetchCities = async () => {
    const response = await fetch(`${BASE_URL}/cities`);
    if (!response.ok) throw new Error("Erreur lors de la récupération des villes.");
    return response.json();
};

export const fetchRestaurantsByCity = async (city) => {

    const response = await fetch(`${BASE_URL}/byCity?city=${encodeURIComponent(city)}`);

    if (!response.ok) throw new Error("Erreur lors de la récupération des restaurants.");
    return response.json();
};
export const fetchAllrestaurants = async () => {

    const response = await fetch(`${BASE_URL}/restaurants`);

    if (!response.ok) throw new Error("Erreur lors de la récupération des restaurants.");
    return response.json();
};
