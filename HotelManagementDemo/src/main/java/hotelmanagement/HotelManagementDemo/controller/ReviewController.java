
package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.Review;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createReview(@RequestBody Review review) {
        reviewService.createReview(review);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Review successfully submitted."));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<SuccessResponse> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        reviewService.updateReview(reviewId, review);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "Review updated successfully."));
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<SuccessResponse> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "Review deleted successfully."));
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> getReviewByRating(@PathVariable int rating) {
        return ResponseEntity.ok(reviewService.getReviewByRating(rating));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Review>> getRecentReviews(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reviewService.getRecentReviews(limit));
    }
}