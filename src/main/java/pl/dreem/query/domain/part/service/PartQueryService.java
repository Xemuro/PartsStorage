package pl.dreem.query.domain.part.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.global.identity.ModelId;
import pl.dreem.global.identity.PartId;
import pl.dreem.query.domain.part.dto.ModelDto;
import pl.dreem.query.domain.part.dto.PartAvailabilityDto;
import pl.dreem.query.domain.part.dto.PartDto;
import pl.dreem.query.domain.part.dto.PartFilterDto;
import pl.dreem.query.domain.part.repository.PartQueryRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartQueryService {

    private final PartQueryRepository repository;

    public PartQueryService(final PartQueryRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional(readOnly = true)
    public Set<PartDto> getPartsByMakeId(final ModelId modelId) {
        Objects.requireNonNull(modelId);
        return repository.findAllByModelsDetails(modelId.getId().toString())
                         .stream()
                         .map(PartDto::fromEntity)
                         .collect(Collectors.toUnmodifiableSet());
    }

    @Transactional(readOnly = true)
    public Map<ModelDto, Set<PartDto>> getPartsWithModels(final PartFilterDto filter) {
        Objects.requireNonNull(filter);
        final Map<ModelDto, Set<PartDto>> result = new HashMap<>();
        final Set<PartEntity> partEntity = repository.findAllByFilters(filter.getNameForQuery(),
                                                                       filter.getDescriptionForQuery());
        partEntity.forEach(park -> park.getModelsDetails()
                                       .stream()
                                       .map(ModelDto::fromEntity)
                                       .forEach(model -> {
                                           final Set<PartDto> parts = result.getOrDefault(model, new HashSet<>());
                                           parts.add(PartDto.fromEntity(park));
                                           result.put(model, parts);
                                       }));
        return result;
    }

    @Transactional(readOnly = true)
    public boolean isPartExist(final PartId partId) {
        return repository.existsById(partId.getIdAsLiteral());
    }

    @Transactional(readOnly = true)
    public Optional<PartAvailabilityDto> getPartAvailability(final PartId partId) {
        Objects.requireNonNull(partId);
        return repository.findByPartId(partId.getIdAsLiteral());
    }
}