package hotelmanagement.HotelManagementDemo.model;

import java.io.Serializable;
import java.util.Objects;

public class RoomAmenityId implements Serializable {
    private int roomId;
    private int amenityId;

    public RoomAmenityId() {}

    public RoomAmenityId(int roomId, int amenityId) {
        this.roomId = roomId;
        this.amenityId = amenityId;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomAmenityId)) return false;
        RoomAmenityId that = (RoomAmenityId) o;
        return roomId == that.roomId && amenityId == that.amenityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, amenityId);
    }
}
