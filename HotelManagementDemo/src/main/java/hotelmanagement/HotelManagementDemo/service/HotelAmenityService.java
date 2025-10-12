package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.model.HotelAmenity;
import hotelmanagement.HotelManagementDemo.repository.AmenityRepository;
import hotelmanagement.HotelManagementDemo.repository.HotelAmenityRepository;
import hotelmanagement.HotelManagementDemo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotelAmenityService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private HotelAmenityRepository hotelAmenityRepository;
    
    public List<HotelAmenity> getAllHotelAmenities() {
        return hotelAmenityRepository.findAll();
    }

    public void addHotelAmenity(int hotelId, int amenityId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND", "Hotel not found"));

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND", "Amenity not found"));

        if (hotelAmenityRepository.existsByHotelAndAmenity(hotel, amenity)) {
            throw new ResourceNotFoundException("ADDFAILS", "Hotelamenity already exist");
        }

        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setHotel(hotel);
        hotelAmenity.setAmenity(amenity);
        hotelAmenity.setAddedDate(new Date());
        hotelAmenity.setActive(true);

        hotelAmenityRepository.save(hotelAmenity);
    }
}