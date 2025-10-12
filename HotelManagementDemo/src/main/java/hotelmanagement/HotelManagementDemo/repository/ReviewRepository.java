package hotelmanagement.HotelManagementDemo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hotelmanagement.HotelManagementDemo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRating(int rating);

    @Query("SELECT r FROM Review r ORDER BY r.reviewDate DESC")
    List<Review> findRecentReviews(PageRequest pageRequest);
}