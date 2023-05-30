package io.github.anfe0690.arithcalcbackend.v1;

import io.github.anfe0690.arithcalcbackend.Utils;
import io.github.anfe0690.arithcalcbackend.v1.operation.OperationEntity;
import io.github.anfe0690.arithcalcbackend.v1.operation.OperationRepository;
import io.github.anfe0690.arithcalcbackend.v1.operation.Type;
import io.github.anfe0690.arithcalcbackend.v1.user.Status;
import io.github.anfe0690.arithcalcbackend.v1.user.UserEntity;
import io.github.anfe0690.arithcalcbackend.v1.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseInitialization implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<UserEntity> optional = userRepository.findById(1L);
        if (!optional.isPresent()) {
            UserEntity user = new UserEntity(1L, "admin@gmail.com", Utils.getPasswordHash("123456"), "Peter Parker",
                    Status.ACTIVE, 100);
            userRepository.save(user);

            for (int i = 1; i<= Type.values().length; i++) {
                OperationEntity operation = new OperationEntity((long) i, Type.values()[i-1], 10*i);
                operationRepository.save(operation);
            }
        }
    }
}
