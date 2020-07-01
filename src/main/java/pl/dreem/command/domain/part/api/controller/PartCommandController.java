package pl.dreem.command.domain.part.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dreem.command.domain.part.api.dto.NewDescriptionRequest;
import pl.dreem.command.domain.part.dto.PartNewDescriptionDto;
import pl.dreem.command.domain.part.service.PartCommandService;
import pl.dreem.command.domain.part.service.SalesCommandService;
import pl.dreem.global.identity.PartId;

import java.util.Objects;
import java.util.UUID;

@Api("Controller do modyfikacji czesci samochodowych - command")
@RequestMapping("/command/v1/part")
@RestController
final class PartCommandController {

    private final PartCommandService partCommandService;
    private final SalesCommandService salesCommandService;

    public PartCommandController(final PartCommandService partCommandService,
                                 final SalesCommandService salesCommandService) {
        this.partCommandService = Objects.requireNonNull(partCommandService);
        this.salesCommandService = Objects.requireNonNull(salesCommandService);
    }

    @ApiOperation("Endpoint do zmiany opisu czesci")
    @PutMapping("/{partId}/description")
    public ResponseEntity changePartDescription(@PathVariable("partId") final UUID partId,
                                                @RequestBody final NewDescriptionRequest request) {
        partCommandService.changePartDescription(PartNewDescriptionDto.from(PartId.from(partId), request));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Endpoint do usunięcia argumentow sprzedażowych")
    @DeleteMapping("/{partId}/sales")
    public ResponseEntity deleteSalesArguments(@PathVariable("partId") final UUID partId){
        salesCommandService.deleteSales(PartId.from(partId));
        return ResponseEntity.noContent().build();
    }
}