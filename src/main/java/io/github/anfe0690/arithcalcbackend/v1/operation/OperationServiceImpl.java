package io.github.anfe0690.arithcalcbackend.v1.operation;

import io.github.anfe0690.arithcalcbackend.v1.record.RecordEntity;
import io.github.anfe0690.arithcalcbackend.v1.record.RecordRepository;
import io.github.anfe0690.arithcalcbackend.v1.user.UserDto;
import io.github.anfe0690.arithcalcbackend.v1.user.UserEntity;
import io.github.anfe0690.arithcalcbackend.v1.user.UserRepository;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Service
public class OperationServiceImpl implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

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

            RecordEntity recordEntity = new RecordEntity(operationEntity.getId(), operationEntity.getType().toString(),
                    userEntity.getId(), operationEntity.getCost(), userEntity.getBalance(), result,
                    new Date().toString());
            recordRepository.save(recordEntity);

            return ResponseEntity.ok(new OperationResultDto(result, sessionUser.balance));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There was an error getting the random string from a external service.");
        }
    }

    private String performOperation(Type type, int firstOperand, int secondOperand) throws IOException {
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
                return generateRandomString();
            default:
                throw new IllegalArgumentException("Unexpected operation type: " + type);
        }
    }

    private String generateRandomString() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://www.random.org/strings/")
                    .addParameter("num", "1")
                    .addParameter("len", "20")
                    .addParameter("digits", "on")
                    .addParameter("upperalpha", "on")
                    .addParameter("loweralpha", "on")
                    .addParameter("unique", "off")
                    .addParameter("format", "plain")
                    .addParameter("rnd", "new")
                    .build();
            return httpclient.execute(httpGet, response -> {
                logger.info("Response status from www.random.org: {} {}", response.getCode(), response.getReasonPhrase());
                return EntityUtils.toString(response.getEntity()).trim();
            });
        }
    }
}
