package hr.tvz.pandza.hardwareapp.controller;

import hr.tvz.pandza.hardwareapp.command.HardwareCommand;
import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAll(){
        return hardwareService.findAll();
    }

    @GetMapping(params = "code")
    public HardwareDTO getHardwareByCode(@RequestParam String code){
        return hardwareService.findByCode(code);
    }

    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody HardwareCommand hardwareCommand){
        return hardwareService.save(hardwareCommand)
                .map((hardwareDTO)-> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO))
                .orElseGet(()->ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
