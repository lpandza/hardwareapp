package hr.tvz.pandza.hardwareapp.security.service;

import hr.tvz.pandza.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
