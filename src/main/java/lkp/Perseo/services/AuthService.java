package lkp.Perseo.services;

import lkp.Perseo.dtos.request.LoginRequest;

import lkp.Perseo.dtos.request.RegisterRequest;
import lkp.Perseo.dtos.response.AuthResponse;
import lkp.Perseo.models.User;
import lkp.Perseo.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        UserDetails user = userRepository.findByUsername(login.getUsername()).orElseThrow();

        String token = jwtService.getTokenService(user);

        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest register) {
        User user = User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(register.getRole ())
                .build();

        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(jwtService.getTokenService(user))
                .role (register.getRole ())
                .build();
    }
}
