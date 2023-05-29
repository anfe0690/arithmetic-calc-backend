package io.github.anfe0690.arithcalcbackend.v1.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class OperationController {

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("operations")
    public List<OperationDto> getOperations() {
        List<OperationDto> operations = new ArrayList<>();
        operationRepository.findAll().forEach(o -> operations.add(new OperationDto(o)));
        return operations;
    }

    @PostMapping("perform-operation")
    public ResponseEntity<?> postPerformOperation(@RequestBody OperationDto operation) {
        return ResponseEntity.ok().build();
    }
}
