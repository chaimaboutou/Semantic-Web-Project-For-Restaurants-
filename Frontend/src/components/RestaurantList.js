import React from "react";
import RestaurantCard from "./RestaurantCard";

const RestaurantList = ({ restaurants }) => {
    return (
        <div className="restaurants-list">
            {restaurants.map((restaurant, index) => (
                <RestaurantCard key={index} restaurant={restaurant} />
            ))}
        </div>
    );
};

export default RestaurantList;
