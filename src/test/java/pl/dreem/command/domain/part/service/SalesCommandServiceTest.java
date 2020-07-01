package pl.dreem.command.domain.part.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dreem.command.domain.part.repository.SalesCommandRepository;
import pl.dreem.global.identity.PartId;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class SalesCommandServiceTest {

    @Mock
    private SalesCommandRepository repository;

    @InjectMocks
    private SalesCommandService sut;

    @Test(expected = NullPointerException.class)
    public void deleteSalesShouldRequirePartId() {
        sut.deleteSales(null);
    }

    @Test
    public void deleteSalesShouldNotThrowExceptionForValidPartId() {
        final PartId testPartId = PartId.from(UUID.randomUUID());
        sut.deleteSales(testPartId);
    }
}
