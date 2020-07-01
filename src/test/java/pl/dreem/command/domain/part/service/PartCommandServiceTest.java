package pl.dreem.command.domain.part.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dreem.command.domain.part.api.dto.NewDescriptionRequest;
import pl.dreem.command.domain.part.dto.PartNewDescriptionDto;
import pl.dreem.command.domain.part.repository.PartCommandRepository;
import pl.dreem.global.identity.PartId;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class PartCommandServiceTest {

    @Mock
    private PartCommandRepository repository;

    @InjectMocks
    private PartCommandService sut;

    @Test(expected = NullPointerException.class)
    public void changePartDescriptionShouldThrowExceptionForNullParameters() {
        sut.changePartDescription(null);
    }

    @Test
    public void changePartDescriptionShouldNotThrowExceptionForValidParameters() {
        final PartNewDescriptionDto testParameter = PartNewDescriptionDto.from(PartId.from(UUID.randomUUID()),
                                                                               NewDescriptionRequest.from(
                                                                                       "description"));
        sut.changePartDescription(testParameter);
    }

}
