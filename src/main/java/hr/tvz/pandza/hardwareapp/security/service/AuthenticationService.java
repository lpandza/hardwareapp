package hr.tvz.pandza.hardwareapp.security.service;

import hr.tvz.pandza.hardwareapp.security.command.LoginCommand;
import hr.tvz.pandza.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
