package hotelmanagement.HotelManagementDemo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "roomamenity")
@IdClass(RoomAmenityId.class)
public class RoomAmenity {

    @Id
    private int roomId;

    @Id
    private int amenityId;

	

    // Optional: Add relationships if needed
}