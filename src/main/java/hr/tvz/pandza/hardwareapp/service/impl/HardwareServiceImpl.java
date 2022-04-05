package hr.tvz.pandza.hardwareapp.service.impl;

import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository repository;

    public HardwareServiceImpl(HardwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return repository.findAll().stream().map(this::mapHardwareToDTO).toList();
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return repository.findByCode(code).map(this::mapHardwareToDTO).orElse(null);
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getPrice());
    }
}
