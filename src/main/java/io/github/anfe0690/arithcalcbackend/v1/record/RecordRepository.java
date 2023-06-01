package io.github.anfe0690.arithcalcbackend.v1.record;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecordRepository extends PagingAndSortingRepository<RecordEntity, Long> {

    List<RecordEntity> findByDeleted(boolean deleted, Pageable pageable);

    List<RecordEntity> findByDeletedAndTypeContainingOrOperationResponseContainingOrDateContainingAllIgnoreCase(boolean deleted,
            String type, String operationResponse, String date, Pageable pageable);
}
