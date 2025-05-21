package com.rudraksh.attendance.service;
import com.rudraksh.attendance.dto.RegisterRequest;
import com.rudraksh.attendance.entity.Role;
import com.rudraksh.attendance.entity.User;
import com.rudraksh.attendance.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rudraksh.attendance.dto.LoginRequest;
import com.rudraksh.attendance.dto.LoginResponse;
import com.rudraksh.attendance.util.JwtUtil;

// Add to fields
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Constructor injection—best practice


    @Override
    public User registerUser(RegisterRequest request) {
        logger.info("Attempting to register user: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            logger.warn("Username already taken: {}", request.getUsername());
            throw new RuntimeException("Username already taken!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.STUDENT)
                .build();

        User savedUser = userRepository.save(user);

        logger.info("User registered successfully: {}", savedUser.getUsername());
        return savedUser;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

}
