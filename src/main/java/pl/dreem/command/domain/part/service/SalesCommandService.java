package pl.dreem.command.domain.part.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.command.domain.part.repository.SalesCommandRepository;
import pl.dreem.global.identity.PartId;

import java.util.Objects;

@Service
public class SalesCommandService {

    private final SalesCommandRepository repository;

    public SalesCommandService(final SalesCommandRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    public void deleteSales(final PartId partId){
        Objects.requireNonNull(partId);
        repository.deleteByPartId(partId.getIdAsLiteral());
    }
}