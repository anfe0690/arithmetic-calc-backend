package io.github.anfe0690.arithcalcbackend.v1;

import io.github.anfe0690.arithcalcbackend.Utils;
import io.github.anfe0690.arithcalcbackend.v1.operation.OperationEntity;
import io.github.anfe0690.arithcalcbackend.v1.operation.OperationRepository;
import io.github.anfe0690.arithcalcbackend.v1.operation.Type;
import io.github.anfe0690.arithcalcbackend.v1.record.RecordEntity;
import io.github.anfe0690.arithcalcbackend.v1.record.RecordRepository;
import io.github.anfe0690.arithcalcbackend.v1.user.Status;
import io.github.anfe0690.arithcalcbackend.v1.user.UserEntity;
import io.github.anfe0690.arithcalcbackend.v1.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseInitialization implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<UserEntity> optional = userRepository.findById(1L);
        if (!optional.isPresent()) {
            UserEntity user = new UserEntity(1L, "admin@gmail.com", Utils.getPasswordHash("123456"), "Peter Parker",
                    Status.ACTIVE, 80);
            userRepository.save(user);

            for (int i = 1; i<= Type.values().length; i++) {
                OperationEntity operation = new OperationEntity((long) i, Type.values()[i-1], 10*i);
                operationRepository.save(operation);
            }

            List<RecordEntity> list = new ArrayList<>();
            list.add(new RecordEntity(1, Type.ADDITION.toString(), 1, 10, 190, "4", new Date().toString()));
            list.add(new RecordEntity(2, Type.SUBTRACTION.toString(), 1, 20, 170, "8", new Date().toString()));
            list.add(new RecordEntity(6, Type.RANDOM_STRING.toString(), 1, 60, 110, "QWERTY", new Date().toString()));
            list.add(new RecordEntity(3, Type.MULTIPLICATION.toString(), 1, 30, 80, "34", new Date().toString(), true));
            for (RecordEntity recordEntity: list) {
                recordRepository.save(recordEntity);
            }
        }
    }
}
