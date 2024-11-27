import React from "react";
import "./CitySelector.css";

const CitySelector = ({ cities, selectedCity, onCityChange }) => {
    return (
        <div className="city-selector">
            {["All", ...cities].map((city) => (
                <button
                    key={city}
                    className={`city-option ${selectedCity === city ? "active" : ""}`}
                    onClick={() => onCityChange({ target: { value: city } })}
                >
                    {city}
                </button>
            ))}
        </div>
    );
};

export default CitySelector;
