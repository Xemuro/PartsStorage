package pl.dreem.command.domain.part.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.command.domain.part.dto.PartNewDescriptionDto;
import pl.dreem.command.domain.part.repository.PartCommandRepository;

import java.util.Objects;

@Service
public class PartCommandService {

    private final PartCommandRepository repository;

    public PartCommandService(final PartCommandRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    public void changePartDescription(final PartNewDescriptionDto partNewDescription) {
        Objects.requireNonNull(partNewDescription);
        repository.changePartDescription(partNewDescription.getId().toString(), partNewDescription.getDescription());
    }
}