package hotelmanagement.HotelManagementDemo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    private String name;
    private String location;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "hotel_amenity",
        joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "hotelId"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "amenityId")

    )
    private Set<Amenity> amenities;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", name=" + name + ", location=" + location + ", description="
				+ description + ", amenities=" + amenities + "]";
	}
    
    

    // Getters and Setters
}