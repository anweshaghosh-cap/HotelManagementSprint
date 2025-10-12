package hotelmanagement.HotelManagementDemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private int roomNumber;
    private boolean isAvailable;
    private String location;
    

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "roomTypeId")
    private RoomType roomType;

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return 
				"Room [roomId=" + roomId + 
		           ", roomNumber=" + roomNumber + 
		           ", isAvailable=" + isAvailable + 
		           ", roomType=" + roomType + 
		           ", location=" + location + "]";

	}
}