package hr.tvz.pandza.hardwareapp.service;

import hr.tvz.pandza.hardwareapp.command.HardwareCommand;
import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

}
