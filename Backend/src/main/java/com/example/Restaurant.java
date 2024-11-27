package com.example;

public class Restaurant {
    private String titre;
    private String adresse;
    private String description;
    private String photo;
    private String city;
    private String amenities;
    private String services;
    

    

    public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	// Getters et Setters
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Restaurant {" +
               "titre='" + titre + '\'' +
               ", adresse='" + adresse + '\'' +
               ", description='" + description + '\'' +
               ", photo='" + photo + '\'' +
               ", city='" + city + '\'' +
               ", amenities='" + amenities + '\'' +
               ", services='" + services + '\'' +
               '}';
    }

}
