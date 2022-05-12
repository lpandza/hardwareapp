package hr.tvz.pandza.hardwareapp.service;

import hr.tvz.pandza.hardwareapp.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDto> findAll();

    List<ReviewDto> findByHardwareCode(String code);

}
