package hotelmanagement.HotelManagementDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hotelmanagement.HotelManagementDemo.model.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    boolean existsByTypeName(String typeName);
}
