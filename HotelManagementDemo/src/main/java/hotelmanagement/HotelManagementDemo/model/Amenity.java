package hotelmanagement.HotelManagementDemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int amenityId;

    private String name;
    private String description;
	public int getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Amenity [amenityId=" + amenityId + ", name=" + name + ", description=" + description + "]";
	}
}