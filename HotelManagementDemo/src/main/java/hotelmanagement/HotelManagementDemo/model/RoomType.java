package hotelmanagement.HotelManagementDemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roomtype")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomTypeId;
    private String typeName;
    private String description;
    private int maxOccupancy;
    @Override
	public String toString() {
		return "RoomType [roomTypeId=" + roomTypeId + ", typeName=" + typeName + ", description=" + description
				+ ", maxOccupancy=" + maxOccupancy + ", pricePerNight=" + pricePerNight + "]";
	}
	public int getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	private double pricePerNight;
}