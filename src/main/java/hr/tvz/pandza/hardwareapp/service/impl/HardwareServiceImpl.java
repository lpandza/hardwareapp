package hr.tvz.pandza.hardwareapp.service.impl;

import hr.tvz.pandza.hardwareapp.command.HardwareCommand;
import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand) {
        return repository.save(toHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getPrice());
    }

    private Hardware toHardware(HardwareCommand hardwareCommand){
        Hardware hardware = new Hardware();
        BeanUtils.copyProperties(hardwareCommand, hardware);
        return hardware;
    }
}
