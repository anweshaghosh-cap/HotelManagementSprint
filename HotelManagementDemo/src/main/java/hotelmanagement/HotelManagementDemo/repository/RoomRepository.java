package hotelmanagement.HotelManagementDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hotelmanagement.HotelManagementDemo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.isAvailable = true AND r.roomType.roomTypeId = :roomTypeId")
    List<Room> findAvailableRoomsByType(@Param("roomTypeId") int roomTypeId);

    //@Query("SELECT r FROM Room r WHERE r.roomType.typeName LIKE %:location%")
    //List<Room> findRoomsByLocation(@Param("location") String location);

    @Query("SELECT r FROM Room r JOIN r.roomType rt JOIN RoomAmenity ra ON r.roomId = ra.roomId WHERE ra.amenityId = :amenityId")
    List<Room> findRoomsByAmenity(@Param("amenityId") int amenityId);
    @Query("SELECT r FROM Room r WHERE r.location LIKE %:location%")
    List<Room> findRoomsByLocation(@Param("location") String location);
}
