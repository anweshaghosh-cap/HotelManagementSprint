package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.RoomType;
import hotelmanagement.HotelManagementDemo.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public RoomType createRoomType(RoomType roomType) {
        if (roomTypeRepository.existsByTypeName(roomType.getTypeName())) {
            throw new ResourceNotFoundException("ADDFAILS", "RoomType already exists");
        }
        return roomTypeRepository.save(roomType);
    }

    public List<RoomType> getAllRoomTypes() {
        List<RoomType> types = roomTypeRepository.findAll();
        if (types.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "RoomType list is empty");
        }
        return types;
    }

    public RoomType getRoomTypeById(int id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS", "RoomType doesn't exist"));
    }

    public RoomType updateRoomType(int id, RoomType updatedType) {
        RoomType type = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UPDTFAILS", "RoomType doesn't exist"));

        type.setTypeName(updatedType.getTypeName());
        type.setDescription(updatedType.getDescription());
        type.setMaxOccupancy(updatedType.getMaxOccupancy());
        type.setPricePerNight(updatedType.getPricePerNight());

        return roomTypeRepository.save(type);
    }

    public void deleteRoomType(int id) {
        RoomType type = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DLTFAILS", "RoomType doesn't exist"));
        roomTypeRepository.delete(type);
    }
}