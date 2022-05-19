package hr.tvz.pandza.hardwareapp.controller;

import hr.tvz.pandza.hardwareapp.command.HardwareCommand;
import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAll(){
        return hardwareService.findAll();
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable String code){
        return hardwareService.findByCode(code)
                .map((hardwareDTO) -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody HardwareCommand hardwareCommand){
        return hardwareService.save(hardwareCommand)
                .map((hardwareDTO)-> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO))
                .orElseGet(()->ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        if (hardwareService.deleteByCode(code)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(path = "/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code,
                                              @Valid @RequestBody HardwareCommand hardwareCommand) {

        return hardwareService.update(code, hardwareCommand)
                .map((hardwareDTO)-> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
