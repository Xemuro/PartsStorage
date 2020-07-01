package pl.dreem.command.domain.recall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreem.api.exceptions.NotFoundPartIdException;
import pl.dreem.command.domain.recall.dto.NewRecallDto;
import pl.dreem.command.domain.recall.repository.RecallCommandRepository;
import pl.dreem.global.entity.RecallEntity;
import pl.dreem.global.identity.RecallId;
import pl.dreem.query.domain.part.service.PartQueryService;

@Service
public class RecallCommandService {

    private final RecallCommandRepository repository;
    private final PartQueryService partQueryService;

    public RecallCommandService(final RecallCommandRepository repository,
                                final PartQueryService partQueryService) {
        this.repository = repository;
        this.partQueryService = partQueryService;
    }

    @Transactional
    public RecallId createNewRecall(NewRecallDto newRecall){
        final boolean isPartExist = partQueryService.isPartExist(newRecall.getPartId());
        if(!isPartExist){
            throw new NotFoundPartIdException();
        }
        final RecallEntity entity = RecallEntity.from(newRecall);
        repository.save(entity);
        return entity.getRecallId();
    }
}
