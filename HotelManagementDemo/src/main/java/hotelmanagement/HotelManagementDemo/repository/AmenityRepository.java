package hotelmanagement.HotelManagementDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hotelmanagement.HotelManagementDemo.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
    boolean existsByName(String name);
    List<Amenity> findByNameContainingIgnoreCase(String keyword);
}
