package hotelmanagement.HotelManagementDemo.repository;

import hotelmanagement.HotelManagementDemo.model.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    boolean existsByName(String name);

    @Query("SELECT h FROM Hotel h JOIN h.amenities a WHERE a.amenityId = :amenityId")
    List<Hotel> findHotelsByAmenityId(@Param("amenityId") int amenityId);

}