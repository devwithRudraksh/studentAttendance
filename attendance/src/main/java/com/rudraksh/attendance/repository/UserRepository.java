package com.rudraksh.attendance.repository;
import com.rudraksh.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // For login later
    boolean existsByUsername(String username);
}
