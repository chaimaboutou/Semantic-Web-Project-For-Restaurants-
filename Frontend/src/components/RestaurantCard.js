import React from "react";
import './RestaurantCard.css';

const RestaurantCard = ({ restaurant }) => {
    const { titre, adresse, description, photo, amenities, services } = restaurant;

    return (
        <div className="restaurant-card">

            <img src={photo} alt={titre} />
            <h2>{titre}</h2>
            <p>Description {description}</p>
            <p>Adresse : {adresse}</p>
            <p>Équipements : {Array.isArray(amenities) ? amenities.join(", ") : amenities}</p>
            <p>Services : {Array.isArray(services) ? services.join(", ") : services}</p>
        </div>
    );
};

export default RestaurantCard;
