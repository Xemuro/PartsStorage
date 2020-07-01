package pl.dreem.query.domain.recall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.global.DateRange;
import pl.dreem.query.domain.recall.dto.RecallDto;
import pl.dreem.query.domain.recall.repository.RecallQueryRepository;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecallQueryService {

    private final RecallQueryRepository repository;

    public RecallQueryService(final RecallQueryRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional(readOnly = true)
    public Set<RecallDto> getRecalls(final DateRange filter) {
        Objects.requireNonNull(filter);
        return repository.findAllByRecallStartBetweenOrRecallFinishBetween(filter.getFrom(), filter.getTo())
                         .stream()
                         .map(RecallDto::fromEntity)
                         .collect(Collectors.toUnmodifiableSet());
    }
}
