package io.github.anfe0690.arithcalcbackend;

import io.github.anfe0690.arithcalcbackend.user.Status;
import io.github.anfe0690.arithcalcbackend.user.UserEntity;
import io.github.anfe0690.arithcalcbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseInitialization implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<UserEntity> optional = userRepository.findById(1L);
        if (!optional.isPresent()) {
            UserEntity user = new UserEntity(1L, "admin@gmail.com", Utils.getPasswordHash("123456"), "Peter Parker",
                    Status.ACTIVE);
            userRepository.save(user);
        }
    }
}
