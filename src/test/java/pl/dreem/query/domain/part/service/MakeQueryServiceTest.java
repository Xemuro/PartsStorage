package pl.dreem.query.domain.part.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dreem.global.entity.ModelEntity;
import pl.dreem.query.domain.part.dto.*;
import pl.dreem.query.domain.part.repository.MakeQueryRepository;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MakeQueryServiceTest {

    @Mock
    private MakeQueryRepository repository;

    @Mock
    private PartQueryService partQueryService;

    @InjectMocks
    private MakeQueryService sut;

    @Test
    public void getMakeAndModelWithPartsShouldInvokeAllPartsForModel() {
        final String expectedMake = "bmw";
        final PartFilterDto filter = PartFilterDto.from(null, null);
        final ModelEntity modelEntity = prepareTestModelEntity(expectedMake);

        when(repository.findAll()).thenReturn(List.of(modelEntity));
        when(partQueryService.getPartsByMakeId(modelEntity.getModelId())).thenReturn(Collections.emptySet());

        final Set<ModelWithPartDto> actualResult = sut.getMakeAndModelWithParts(filter);
        assertThat(actualResult.size(), is(1));
    }

    @Test
    public void getMakeAndModelWithPartsShouldFetchPartsWithModels() {
        final String expectedMake = "bmw";
        final PartFilterDto filter = PartFilterDto.from("model", "make");
        final ModelEntity modelEntity = prepareTestModelEntity(expectedMake);
        final ModelDto modelDto = preapreTestModelDto(modelEntity);
        Map<ModelDto, Set<PartDto>> expectedResult = new HashMap<>();
        expectedResult.put(modelDto, Collections.emptySet());

        when(partQueryService.getPartsWithModels(filter)).thenReturn(expectedResult);

        final Set<ModelWithPartDto> actualResult = sut.getMakeAndModelWithParts(filter);
        assertThat(actualResult.size(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void getMakeAndModelWithPartsShouldRequireFilter() {
        sut.getMakeAndModelWithParts(null);
    }

    @Test
    public void getMakeDetailsShouldReturnValidResponse() {
        final String expectedMake = "bmw";
        final List<@NotNull ModelEntity> mockEntity = List.of(prepareTestModelEntity(expectedMake));

        when(repository.findByMakeIgnoreCase(expectedMake)).thenReturn(mockEntity);
        final MakeDetailsDto actualResult = sut.getMakeDetails(expectedMake);

        assertThat(actualResult.getMake(), is(equalTo(expectedMake)));
        assertThat(actualResult.getModels().size(), is(1));

        final Set<ModelDetailsDto> models = actualResult.getModels();
        final ModelDetailsDto modelDetailsDto = models.stream().findFirst().get();

        assertThat(modelDetailsDto.getModel(), is(mockEntity.get(0).getModel()));
        assertThat(modelDetailsDto.getId().getIdAsLiteral(), is(mockEntity.get(0).getId()));
    }

    @NotNull
    private ModelDto preapreTestModelDto(ModelEntity modelEntity) {
        return ModelDto.fromEntity(modelEntity);
    }

    @NotNull
    private ModelEntity prepareTestModelEntity(String make) {
        final ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(UUID.randomUUID().toString());
        modelEntity.setMake(make);
        modelEntity.setModel("318d");
        modelEntity.setProductionStart(LocalDate.of(2010, 01, 01));
        modelEntity.setProductionHold(LocalDate.of(2015, 01, 01));
        modelEntity.setParts(Collections.emptyList());
        return modelEntity;
    }
}