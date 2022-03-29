package hr.tvz.pandza.hardwareapp.service;

import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;

import java.util.List;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

}
