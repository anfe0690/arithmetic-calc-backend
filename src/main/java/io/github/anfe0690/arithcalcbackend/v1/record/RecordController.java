package io.github.anfe0690.arithcalcbackend.v1.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) List<String> sortBy,
            @RequestParam(required = false) List<Sort.Direction> sortDirection)
    {
        List<RecordDto> list = new ArrayList<>();
        if (sortBy == null || sortBy.isEmpty()) {
            recordRepository.findAll(PageRequest.of(page, recordsPerPage)).forEach(r -> list.add(new RecordDto(r)));
        }
        else {
            Sort sort = Sort.by((sortDirection == null || sortDirection.isEmpty()) ? Sort.Direction.ASC :
                    sortDirection.get(0), sortBy.get(0));
            for (int i=1; i<sortBy.size(); i++) {
                boolean isValidSortDirectionIndex = sortDirection != null && !sortDirection.isEmpty()
                        && i < sortDirection.size();
                sort = sort.and(Sort.by(isValidSortDirectionIndex ? sortDirection.get(i) : Sort.Direction.ASC,
                        sortBy.get(i)));
            }
            recordRepository.findAll(PageRequest.of(page, recordsPerPage, sort)).forEach(r -> list.add(new RecordDto(r)));
        }
        return list;
    }
}
