package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Review;
import hotelmanagement.HotelManagementDemo.repository.ReviewRepository;
import hotelmanagement.HotelManagementDemo.service.ReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    private Review sampleReview;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleReview = new Review();
       // sampleReview.setId(1L);
        sampleReview.setRating(5);
        sampleReview.setComment("Excellent stay!");
       // sampleReview.setReviewDate(LocalDate.now());
    }

    @Test
    void testCreateReview() {
        when(reviewRepository.save(sampleReview)).thenReturn(sampleReview);
        Review result = reviewService.createReview(sampleReview);
        assertEquals(sampleReview, result);
    }

    @Test
    void testGetAllReviews_Success() {
        when(reviewRepository.findAll()).thenReturn(List.of(sampleReview));
        List<Review> result = reviewService.getAllReviews();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllReviews_Empty() {
        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> reviewService.getAllReviews());
    }

    @Test
    void testGetReviewById_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(sampleReview));
        Review result = reviewService.getReviewById(1L);
        assertEquals(5, result.getRating());
    }

    @Test
    void testGetReviewById_NotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> reviewService.getReviewById(1L));
    }

    @Test
    void testUpdateReview_Success() {
        Review updatedReview = new Review();
        updatedReview.setRating(4);
        updatedReview.setComment("Good experience");
        //updatedReview.setReviewDate(LocalDate.now());

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(sampleReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReview);

        Review result = reviewService.updateReview(1L, updatedReview);
        assertEquals(4, result.getRating());
        assertEquals("Good experience", result.getComment());
    }

    @Test
    void testDeleteReview_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(sampleReview));
        doNothing().when(reviewRepository).delete(sampleReview);

        assertDoesNotThrow(() -> reviewService.deleteReview(1L));
        verify(reviewRepository, times(1)).delete(sampleReview);
    }

    @Test
    void testGetReviewByRating_Success() {
        when(reviewRepository.findByRating(5)).thenReturn(List.of(sampleReview));
        List<Review> result = reviewService.getReviewByRating(5);
        assertEquals(1, result.size());
    }

    @Test
    void testGetReviewByRating_Empty() {
        when(reviewRepository.findByRating(3)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> reviewService.getReviewByRating(3));
    }

    @Test
    void testGetRecentReviews_Success() {
        when(reviewRepository.findRecentReviews(PageRequest.of(0, 5))).thenReturn(List.of(sampleReview));
        List<Review> result = reviewService.getRecentReviews(5);
        assertEquals(1, result.size());
    }

    @Test
    void testGetRecentReviews_Empty() {
        when(reviewRepository.findRecentReviews(PageRequest.of(0, 5))).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> reviewService.getRecentReviews(5));
    }
}