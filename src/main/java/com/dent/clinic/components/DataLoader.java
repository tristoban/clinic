package com.dent.clinic.components;


import com.dent.clinic.entities.AppUser;
import com.dent.clinic.enums.AppUserRole;
import com.dent.clinic.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin123");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("user123");
        userRepository.save(new AppUser(0L, "Admin", "admin", "admin@fullcomedor.com", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser(0L,"User", "user", "user@fullcomedor.com", hashedPassword2, AppUserRole.USER));
    }
}
