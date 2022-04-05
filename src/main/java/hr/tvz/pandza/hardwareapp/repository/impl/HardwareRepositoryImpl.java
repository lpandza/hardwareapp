package hr.tvz.pandza.hardwareapp.repository.impl;

import hr.tvz.pandza.hardwareapp.enums.Type;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class HardwareRepositoryImpl implements HardwareRepository {

    private final List<Hardware> HARDWARE = Arrays.asList(
            new Hardware("1111", "Razer DeathAdder", 300D, Type.OTHER, 3),
            new Hardware("2222", "AMD Ryzen 5", 2000D, Type.CPU, 11),
            new Hardware("3333", "Intel i5", 2000D, Type.CPU,5)
    );

    @Override
    public List<Hardware> findAll() {
        return HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return HARDWARE.stream().filter(h -> h.getCode().equals(code)).findFirst();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        Optional<Hardware> hardwareOptional = HARDWARE.stream().filter(h -> h.getCode().equals(hardware.getCode())).findAny();
        if (hardwareOptional.isEmpty()){
            HARDWARE.add(hardware);
        }

        return hardwareOptional;
    }
}
