package pl.dreem.query.domain.part.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.query.domain.part.dto.*;
import pl.dreem.query.domain.part.jpa.repository.MakeQueryRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MakeQueryService {

    private final MakeQueryRepository repository;
    private final PartQueryService partQueryService;

    public MakeQueryService(final MakeQueryRepository repository,
                            final PartQueryService partQueryService) {
        this.repository = Objects.requireNonNull(repository);
        this.partQueryService = Objects.requireNonNull(partQueryService);
    }

    public MakeDetailsDto getMakeDetails(final String make) {
        Objects.requireNonNull(make);
        final Set<ModelDetailsDto> models = repository.findByMakeIgnoreCase(make)
                                                      .stream()
                                                      .map(ModelDto::fromEntity)
                                                      .map(model -> ModelDetailsDto.from(model, partQueryService.getPartsByMakeId(model.getId())))
                                                      .collect(Collectors.toUnmodifiableSet());
        final MakeDetailsDto result = MakeDetailsDto.from(make, models);
        return result;
    }

    @Transactional(readOnly = true)
    public Set<ModelWithPartDto> getMakeAndModelWithParts(final PartFilterDto filter) {
        Objects.requireNonNull(filter);
        Set<ModelWithPartDto> result;
        if (filter.isFilteringRequired()) {
            result = getPartsForAllMake(filter);
        } else {
            result = getPartsForAllMake();
        }
        return result;
    }

    private Set<ModelWithPartDto> getPartsForAllMake(final PartFilterDto filter) {
        final Map<ModelDto, Set<PartDto>> partsForModels = partQueryService.getPartsWithModels(filter);
        return partsForModels.entrySet()
                           .stream()
                           .map(item -> ModelWithPartDto.from(item.getKey(), item.getValue()))
                           .collect(Collectors.toUnmodifiableSet());
    }

    private Set<ModelWithPartDto> getPartsForAllMake() {
        return repository.findAll()
                         .stream()
                         .map(ModelDto::fromEntity)
                         .map(model -> ModelWithPartDto.from(model, partQueryService.getPartsByMakeId(model.getId())))
                         .collect(Collectors.toUnmodifiableSet());
    }
}