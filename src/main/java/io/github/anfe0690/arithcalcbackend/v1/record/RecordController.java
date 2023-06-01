package io.github.anfe0690.arithcalcbackend.v1.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/")
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("records")
    public List<RecordDto> getRecords(@RequestParam(defaultValue = "10") int recordsPerPage,
            @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) List<String> sortBy,
            @RequestParam(required = false) List<Sort.Direction> sortDirection, @RequestParam(required = false) String search)
    {
        Pageable pageable;
        if (sortBy == null || sortBy.isEmpty()) {
            pageable = PageRequest.of(page, recordsPerPage);
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
            pageable = PageRequest.of(page, recordsPerPage, sort);
        }

        if (search == null || search.isEmpty()) {
            return recordRepository.findAll(pageable).stream().map(RecordDto::new).collect(Collectors.toList());
        }
        else {
            return recordRepository.findByTypeContainingOrOperationResponseContainingOrDateContainingAllIgnoreCase(search,
                    search, search, pageable).stream().map(RecordDto::new).collect(Collectors.toList());
        }
    }
}
