
package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.model.RoomAmenity;
import hotelmanagement.HotelManagementDemo.repository.RoomAmenityRepository;
import hotelmanagement.HotelManagementDemo.service.RoomAmenityService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomAmenityServiceTest {

    @Mock
    private RoomAmenityRepository roomAmenityRepository;

    @InjectMocks
    private RoomAmenityService roomAmenityService;

    public RoomAmenityServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRoomAmenity() {
        RoomAmenity roomAmenity = new RoomAmenity();
        when(roomAmenityRepository.save(roomAmenity)).thenReturn(roomAmenity);

        RoomAmenity result = roomAmenityService.addRoomAmenity(roomAmenity);
        assertNotNull(result);
        verify(roomAmenityRepository, times(1)).save(roomAmenity);
    }

    @Test
    void testGetAmenitiesByRoomIdThrowsExceptionWhenEmpty() {
        when(roomAmenityRepository.findAmenitiesByRoomId(1)).thenReturn(Collections.emptyList());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                roomAmenityService.getAmenitiesByRoomId(1));

        assertEquals("GETALLFAILS", exception.getCode());
    }

    @Test
    void testGetRoomsByAmenityIdThrowsExceptionWhenEmpty() {
        when(roomAmenityRepository.findRoomsByAmenityId(1)).thenReturn(Collections.emptyList());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                roomAmenityService.getRoomsByAmenityId(1));

        assertEquals("GETALLFAILS", exception.getCode());
    }
}