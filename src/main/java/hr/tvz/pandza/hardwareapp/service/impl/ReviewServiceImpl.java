package hr.tvz.pandza.hardwareapp.service.impl;

import hr.tvz.pandza.hardwareapp.dto.ReviewDto;
import hr.tvz.pandza.hardwareapp.model.Review;
import hr.tvz.pandza.hardwareapp.repository.ReviewRepository;
import hr.tvz.pandza.hardwareapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream().map(this::toReviewDto).toList();
    }

    @Override
    public List<ReviewDto> findByHardwareCode(String code) {
        return reviewRepository.findReviewByHardwareCode(code).stream().map(this::toReviewDto).toList();
    }

    private ReviewDto toReviewDto(Review review){
        return new ReviewDto(review.getId(), review.getTitle(), review.getText(), review.getScore());
    }
}
