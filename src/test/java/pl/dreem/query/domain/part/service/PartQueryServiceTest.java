package pl.dreem.query.domain.part.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import pl.dreem.global.entity.ModelEntity;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.global.identity.ModelId;
import pl.dreem.global.identity.PartId;
import pl.dreem.query.domain.part.dto.ModelDto;
import pl.dreem.query.domain.part.dto.PartAvailabilityDto;
import pl.dreem.query.domain.part.dto.PartDto;
import pl.dreem.query.domain.part.dto.PartFilterDto;
import pl.dreem.query.domain.part.repository.PartQueryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartQueryServiceTest {

    @Mock
    private PartQueryRepository repository;

    @InjectMocks
    private PartQueryService sut;

    @Test
    public void isPartExistShouldReturnTrueForExistPartId() {
        final PartId testPartId = prepareTestPartId();

        when(repository.existsById(testPartId.getIdAsLiteral())).thenReturn(true);

        final boolean actualResult = sut.isPartExist(testPartId);

        assertThat(actualResult, is(true));
    }

    @Test
    public void isPartExistShouldReturnFalseForNotExistPartId() {
        final PartId testPartId = prepareTestPartId();

        when(repository.existsById(testPartId.getIdAsLiteral())).thenReturn(false);

        final boolean actualResult = sut.isPartExist(testPartId);

        assertThat(actualResult, is(false));
    }

    @Test
    public void getPartAvailabilityShouldReturnPartInformationIfExist() {
        final PartId testPartId = prepareTestPartId();

        final PartAvailabilityDto mockPart = prepareMockAvailability(testPartId);
        final Optional<PartAvailabilityDto> mockResult = Optional.of(mockPart);

        when(repository.findByPartId(testPartId.getIdAsLiteral())).thenReturn(mockResult);

        final Optional<PartAvailabilityDto> actualResult = sut.getPartAvailability(testPartId);

        assertThat(actualResult, is(mockResult));
    }

    @Test
    public void getPartAvailabilityShouldReturnEmptyResultForNotExistPart() {
        final PartId testPartId = prepareTestPartId();

        final Optional<PartAvailabilityDto> mockResult = Optional.empty();

        when(repository.findByPartId(testPartId.getIdAsLiteral())).thenReturn(mockResult);

        final Optional<PartAvailabilityDto> actualResult = sut.getPartAvailability(testPartId);

        assertThat(actualResult, is(mockResult));
    }

    @Test(expected = NullPointerException.class)
    public void getPartAvailabilityShouldThrowExceptionForNullParameter() {
        sut.getPartAvailability(null);
    }

    @Test(expected = NullPointerException.class)
    public void getPartsByMakeIdShouldThrowExceptionForNotExistParameter() {
        sut.getPartsByMakeId(null);
    }

    @Test
    public void getPartsByMakeIdShouldReturnPartForMake() {
        final ModelId expectedModelId = ModelId.from(UUID.randomUUID());
        final ModelEntity testModelEntity = prepareModelEntity(expectedModelId);

        final PartId testPartId = prepareTestPartId();
        final PartEntity expectedPart = preparePartEntity(testPartId, testModelEntity);
        final Set<PartEntity> mockParts = Set.of(expectedPart);

        when(repository.findAllByModelsDetails(expectedModelId.getIdAsLiteral())).thenReturn(mockParts);

        final Set<PartDto> actualResult = sut.getPartsByMakeId(expectedModelId);

        assertThat(actualResult.size(), is(1));

        final PartDto partDto = actualResult.stream()
                                            .findFirst()
                                            .get();

        assertThat(partDto.getPartId(), is(equalTo(testPartId)));
        assertThat(partDto.getDescription(), is(equalTo(expectedPart.getDescription())));
    }

    @Test
    public void getPartsByMakeIdShouldReturnEmptyResultForNotFoundMake() {
        final ModelId expectedModelId = ModelId.from(UUID.randomUUID());

        when(repository.findAllByModelsDetails(expectedModelId.getIdAsLiteral())).thenReturn(Collections.emptySet());
        final Set<PartDto> actualResult = sut.getPartsByMakeId(expectedModelId);

        assertThat(actualResult.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void getPartsWithModelsShouldThrowExceptionForNullParameter() {
        sut.getPartsWithModels(null);
    }

    @Test
    public void getPartsWithModelsShouldReturnEmptyResultForNotFoundPart() {
        final PartFilterDto filter = PartFilterDto.from("model", "description");

        when(repository.findAllByFilters(filter.getNameForQuery(), filter.getDescriptionForQuery())).thenReturn(
                Collections.emptySet());

        final Map<ModelDto, Set<PartDto>> actualResult = sut.getPartsWithModels(filter);

        assertThat(actualResult.size(), is(0));
    }

    @Test
    public void getPartsWithModelsShouldReturnModelAndPartsForIt() {
        final PartId expectedPartId = prepareTestPartId();
        final ModelId testModelId = ModelId.from(UUID.randomUUID());
        final ModelEntity testModelEntity = prepareModelEntity(testModelId);
        final ModelDto testModelDto = ModelDto.fromEntity(testModelEntity);
        final PartEntity testPartEntity = preparePartEntity(expectedPartId, testModelEntity);
        final Set<PartEntity> mockParts = Set.of(testPartEntity);

        final PartFilterDto filter = PartFilterDto.from("model", "description");

        when(repository.findAllByFilters(filter.getNameForQuery(), filter.getDescriptionForQuery())).thenReturn(
                mockParts);

        final Map<ModelDto, Set<PartDto>> actualResult = sut.getPartsWithModels(filter);

        assertThat(actualResult.size(), is(1));

        final Set<PartDto> actualParts = actualResult.get(testModelDto);
        final PartDto actualPart = actualParts.stream().findFirst().get();

        assertThat(actualParts.size(), is(1));
        assertThat(actualPart.getPartId(), is(expectedPartId));
    }

    private PartEntity preparePartEntity(final PartId testPartId, final ModelEntity modelEntity) {
        final PartEntity partEntity = new PartEntity();
        partEntity.setId(testPartId.getIdAsLiteral());
        partEntity.setName("name");
        partEntity.setDescription("description");
        partEntity.setAvailable(true);
        partEntity.setNumberOfDaysToShip(1);
        partEntity.setPrice(BigDecimal.ONE);
        partEntity.setSalesArguments(Collections.emptyList());
        partEntity.setModelsDetails(List.of(modelEntity));
        return partEntity;
    }

    @NotNull
    private ModelEntity prepareModelEntity(final ModelId modelId) {
        final ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(modelId.getIdAsLiteral());
        modelEntity.setMake("BMW");
        modelEntity.setModel("318d");
        modelEntity.setProductionStart(LocalDate.MIN);
        modelEntity.setProductionHold(LocalDate.MAX);
        return modelEntity;
    }

    @NotNull
    private PartAvailabilityDto prepareMockAvailability(PartId testPartId) {
        return new PartAvailabilityDto(testPartId.getIdAsLiteral(), true, 5);
    }

    @NotNull
    private PartId prepareTestPartId() {
        return PartId.from(UUID.randomUUID());
    }

}
