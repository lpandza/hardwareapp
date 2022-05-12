package hr.tvz.pandza.hardwareapp.service.impl;

import hr.tvz.pandza.hardwareapp.command.HardwareCommand;
import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.dto.ReviewDto;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import hr.tvz.pandza.hardwareapp.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository repository;
    private final ReviewService reviewService;

    public HardwareServiceImpl(HardwareRepository repository, ReviewService reviewService) {
        this.repository = repository;
        this.reviewService = reviewService;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return repository.findAll().stream().map(this::mapHardwareToDTO).toList();
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return repository.findByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand) {
        return repository.save(toHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public boolean deleteByCode(String code) {
        return repository.deleteByCode(code);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand hardwareCommand) {
        return repository.update(code, toHardware(hardwareCommand))
                .map(this::mapHardwareToDTO);
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware) {
        List<ReviewDto> reviewDtoList = reviewService.findByHardwareCode(hardware.getCode());
        return new HardwareDTO(hardware.getCode(), hardware.getName(), hardware.getPrice(), reviewDtoList);
    }

    private Hardware toHardware(HardwareCommand hardwareCommand){
        Hardware hardware = new Hardware();
        BeanUtils.copyProperties(hardwareCommand, hardware);
        return hardware;
    }
}
