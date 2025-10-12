package hotelmanagement.HotelManagementDemo.repository;

import hotelmanagement.HotelManagementDemo.model.HotelAmenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Integer> {
    boolean existsByHotelAndAmenity(Hotel hotel, Amenity amenity);
}