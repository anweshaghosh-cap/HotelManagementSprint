package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.repository.HotelRepository;
import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getHotelsByAmenityId(int amenityId) {
        List<Hotel> hotels = hotelRepository.findHotelsByAmenityId(amenityId);
        if (hotels.isEmpty()) {
            throw new ResourceNotFoundException("GETFAILS", "No hotel is found with the specific amenity");
        }
        return hotels;
    }

    public List<Amenity> getAmenitiesByHotelId(int hotelId) {
        Hotel hotel = getHotelById(hotelId);
        List<Amenity> amenities = new ArrayList<>(hotel.getAmenities());
        if (amenities.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No amenities found for the given hotel");
        }
        return amenities;
    }

    public Hotel createHotel(Hotel hotel) {
        if (hotelRepository.existsByName(hotel.getName())) {
            throw new ResourceNotFoundException("ADDFAILS", "Hotel already exists");
        }
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        if (hotels.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "Hotel list is empty");
        }
        return hotels;
    }

    public Hotel getHotelById(int id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS", "Hotel not found"));
    }

    public Hotel updateHotel(int id, Hotel updatedHotel) {
        Hotel hotel = getHotelById(id);
        hotel.setName(updatedHotel.getName());
        hotel.setLocation(updatedHotel.getLocation());
        hotel.setDescription(updatedHotel.getDescription());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DLTFAILS", "Hotel doesn't exist"));
        hotelRepository.delete(hotel);
    }
}