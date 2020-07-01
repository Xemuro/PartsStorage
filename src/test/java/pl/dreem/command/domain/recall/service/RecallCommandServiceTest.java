package pl.dreem.command.domain.recall.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dreem.api.exceptions.NotFoundPartIdException;
import pl.dreem.command.domain.recall.api.request.NewRecallRequest;
import pl.dreem.command.domain.recall.dto.NewRecallDto;
import pl.dreem.command.domain.recall.repository.RecallCommandRepository;
import pl.dreem.global.DateRange;
import pl.dreem.global.identity.PartId;
import pl.dreem.global.identity.RecallId;
import pl.dreem.query.domain.part.service.PartQueryService;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class RecallCommandServiceTest {

    @Mock
    private RecallCommandRepository repository;

    @Mock
    private PartQueryService partQueryService;

    @InjectMocks
    private RecallCommandService sut;

    @Test(expected = NotFoundPartIdException.class)
    public void createNewRecallShouldThrowExceptionWhenPartNotExist() {
        final PartId expectedPartId = PartId.from(UUID.randomUUID());
        Mockito.when(partQueryService.isPartExist(expectedPartId)).thenReturn(false);

        final NewRecallDto testRequest = prepareTestData(expectedPartId);

        sut.createNewRecall(testRequest);
    }

    @Test
    public void createNewRecallShouldReturnCreatedId() {
        final PartId expectedPartId = PartId.from(UUID.randomUUID());
        Mockito.when(partQueryService.isPartExist(expectedPartId)).thenReturn(true);

        final NewRecallDto testRequest = prepareTestData(expectedPartId);

        final RecallId actualId = sut.createNewRecall(testRequest);

        assertThat(actualId, is(notNullValue()));
    }

    @NotNull
    private NewRecallDto prepareTestData(PartId expectedPartId) {
        final DateRange duration = DateRange.from(LocalDate.MIN, LocalDate.MAX);
        final NewRecallRequest request = NewRecallRequest.from("name", duration, expectedPartId);
        return NewRecallDto.from(request);
    }
}