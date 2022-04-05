package hr.tvz.pandza.hardwareapp.repository;

import hr.tvz.pandza.hardwareapp.model.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {
    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);
}
