package io.github.anfe0690.arithcalcbackend.v1.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("records")
    public List<RecordDto> getRecords(@RequestParam(defaultValue = "10") int recordsPerPage,
                                      @RequestParam(defaultValue = "0") int page) {
        List<RecordDto> list = new ArrayList<>();
        recordRepository.findAll(PageRequest.of(page, recordsPerPage)).forEach(r -> list.add(new RecordDto(r)));
        return list;
    }
}
