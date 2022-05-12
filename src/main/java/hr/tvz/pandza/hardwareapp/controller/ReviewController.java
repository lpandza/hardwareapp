package hr.tvz.pandza.hardwareapp.controller;


import hr.tvz.pandza.hardwareapp.dto.ReviewDto;
import hr.tvz.pandza.hardwareapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAll(){
        return reviewService.findAll();
    }

    @GetMapping(path = "/{hardwareCode}")
    public List<ReviewDto> getByHardwareCode(@PathVariable String hardwareCode){
        return reviewService.findByHardwareCode(hardwareCode);
//                .map(reviewDto -> ResponseEntity.status(HttpStatus.OK).body(reviewDto))
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
