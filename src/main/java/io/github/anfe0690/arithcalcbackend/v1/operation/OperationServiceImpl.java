package io.github.anfe0690.arithcalcbackend.v1.operation;

import io.github.anfe0690.arithcalcbackend.v1.record.RecordEntity;
import io.github.anfe0690.arithcalcbackend.v1.record.RecordRepository;
import io.github.anfe0690.arithcalcbackend.v1.user.UserDto;
import io.github.anfe0690.arithcalcbackend.v1.user.UserEntity;
import io.github.anfe0690.arithcalcbackend.v1.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    @Override
    public ResponseEntity<?> performOperation(OperationDto operation, HttpSession session) {
        OperationEntity operationEntity = operationRepository.findByType(operation.type);
        if (operationEntity == null) {
            return ResponseEntity.badRequest().body("Operation type not found: " + operation.type);
        }

        UserDto sessionUser = (UserDto) session.getAttribute("user");
        if (sessionUser.balance < operationEntity.getCost()) {
            return ResponseEntity.badRequest().body("The user doesn't have enough balance for the " + operation.type
                    + " operation.");
        }

        try {
            String result = performOperation(operation.type, operation.firstOperand, operation.secondOperand);

            UserEntity userEntity = userRepository.findByUsername(sessionUser.username);
            userEntity.setBalance(userEntity.getBalance() - operationEntity.getCost());
            userRepository.save(userEntity);
            sessionUser.balance = userEntity.getBalance();

            RecordEntity recordEntity = new RecordEntity(operationEntity.getId(), userEntity.getId(),
                    operationEntity.getCost(), userEntity.getBalance(), result, new Date());
            recordRepository.save(recordEntity);

            return ResponseEntity.ok(new OperationResultDto(result, sessionUser.balance));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String performOperation(Type type, int firstOperand, int secondOperand) {
        switch (type) {
            case ADDITION:
                return Integer.toString(firstOperand + secondOperand);
            case SUBTRACTION:
                return Integer.toString(firstOperand - secondOperand);
            case MULTIPLICATION:
                return Integer.toString(firstOperand * secondOperand);
            case DIVISION:
                return Double.toString(firstOperand * 1.0 / secondOperand);
            case SQUARE_ROOT:
                return Double.toString(Math.sqrt(firstOperand));
            case RANDOM_STRING:
                throw new RuntimeException("Not implemented");
            default:
                throw new IllegalArgumentException("Unexpected operation type: " + type);
        }
    }
}
