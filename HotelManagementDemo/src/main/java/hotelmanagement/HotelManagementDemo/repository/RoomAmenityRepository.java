package hotelmanagement.HotelManagementDemo.repository;


import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.model.RoomAmenity;
import hotelmanagement.HotelManagementDemo.model.RoomAmenityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, RoomAmenityId> {

    @Query("SELECT a FROM Amenity a WHERE a.amenityId IN (SELECT ra.amenityId FROM RoomAmenity ra WHERE ra.roomId = :roomId)")
    List<Amenity> findAmenitiesByRoomId(@Param("roomId") int roomId);

    @Query("SELECT r FROM Room r WHERE r.roomId IN (SELECT ra.roomId FROM RoomAmenity ra WHERE ra.amenityId = :amenityId)")
    List<Room> findRoomsByAmenityId(@Param("amenityId") int amenityId);
}
