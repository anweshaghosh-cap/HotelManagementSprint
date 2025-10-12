package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Review;
import hotelmanagement.HotelManagementDemo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        // You can add validation or duplicate checks here if needed
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No reviews available.");
        }
        return reviews;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS", "Review with ID " + id + " not found."));
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UPDTFAILS", "Review with ID " + id + " not found."));

        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setReviewDate(updatedReview.getReviewDate());

        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DLTFAILS", "Review with ID " + id + " not found."));
        reviewRepository.delete(review);
    }

    public List<Review> getReviewByRating(int rating) {
        List<Review> reviews = reviewRepository.findByRating(rating);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No reviews found with rating: " + rating);
        }
        return reviews;
    }
//To get the top 5 reviews 
    public List<Review> getRecentReviews(int limit) {
        List<Review> reviews = reviewRepository.findRecentReviews(PageRequest.of(0, limit));
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("GETFAILS", "No recent reviews found.");
        }
        return reviews;
    }
}