package pl.dreem.query.domain.recall.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dreem.global.DateRange;
import pl.dreem.global.entity.RecallEntity;
import pl.dreem.query.domain.recall.dto.RecallDto;
import pl.dreem.query.domain.recall.repository.RecallQueryRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RecallQueryServiceTest {

    @Mock
    private RecallQueryRepository repository;

    @InjectMocks
    private RecallQueryService sut;

    @Test
    public void getRecallsShouldReturnValidResult() {

        final RecallEntity recallEntity = new RecallEntity();
        final Set<RecallEntity> mockResult = prepareTestRecallEntity(recallEntity);

        final LocalDate expectedFrom = LocalDate.of(2015, 01, 01);
        final LocalDate expectedTo = LocalDate.of(2016, 01, 01);

        final DateRange filter = DateRange.from(expectedFrom, expectedTo);

        Mockito.when(
                repository.findRecallsByDate(expectedFrom, expectedTo))
               .thenReturn(mockResult);

        final Set<RecallDto> actualResult = sut.getRecalls(filter);

        assertThat(actualResult.size(), is(1));

        final RecallDto actualResultDto = actualResult.stream().findFirst().get();

        assertThat(actualResultDto.getId(), is(equalTo(recallEntity.getRecallId())));
        assertThat(actualResultDto.getName(), is(equalTo(recallEntity.getName())));
        assertThat(actualResultDto.getPartId(), is(equalTo(recallEntity.getPartIdAsId())));
    }

    @Test(expected = NullPointerException.class)
    public void getRecallsShouldRequireFilter() {
        sut.getRecalls(null);
    }

    @NotNull
    private Set<RecallEntity> prepareTestRecallEntity(RecallEntity recallEntity) {
        recallEntity.setId(UUID.randomUUID().toString());
        recallEntity.setName("name");
        recallEntity.setRecallStart(LocalDate.of(2015, 05, 01));
        recallEntity.setRecallFinish(LocalDate.of(2015, 06, 01));
        recallEntity.setPartId(UUID.randomUUID().toString());

        return Set.of(recallEntity);
    }

}
