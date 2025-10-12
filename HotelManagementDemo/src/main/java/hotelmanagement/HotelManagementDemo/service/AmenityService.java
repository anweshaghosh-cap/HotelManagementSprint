package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository repository;

    public Amenity createAmenity(Amenity amenity) {
        boolean alreadyExists = repository.existsByName(amenity.getName());
        if (alreadyExists) {
            throw new ResourceNotFoundException("CREATE_FAIL", "Amenity with this name already exists.");
        }
        return repository.save(amenity);
    }

    public List<Amenity> getAllAmenities() {
        List<Amenity> amenities = repository.findAll();
        if (amenities.isEmpty()) {
            throw new ResourceNotFoundException("FETCH_FAIL", "No amenities found in the system.");
        }
        return amenities;
    }

    public Amenity getAmenityById(int amenityId) {
        return repository.findById(amenityId)
                .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND", "Amenity with ID " + amenityId + " not found."));
    }

    public Amenity updateAmenity(int amenityId, Amenity updatedAmenity) {
        Amenity existingAmenity = getAmenityById(amenityId);
        existingAmenity.setName(updatedAmenity.getName());
        existingAmenity.setDescription(updatedAmenity.getDescription());
        return repository.save(existingAmenity);
    }

    public void deleteAmenity(int amenityId) {
        Amenity amenityToDelete = getAmenityById(amenityId);
        repository.delete(amenityToDelete);
    }
}