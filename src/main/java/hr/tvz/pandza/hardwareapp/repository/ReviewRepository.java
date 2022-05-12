package hr.tvz.pandza.hardwareapp.repository;

import hr.tvz.pandza.hardwareapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewByHardwareCode(String hardwareCode);

}
